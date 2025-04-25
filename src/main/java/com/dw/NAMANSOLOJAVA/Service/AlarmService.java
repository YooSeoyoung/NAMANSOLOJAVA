package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.DTO.WeatherDTO;
import com.dw.NAMANSOLOJAVA.Repository.AlarmRepository;
import com.dw.NAMANSOLOJAVA.Repository.AlarmSettingRepository;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import com.dw.NAMANSOLOJAVA.model.Alarm;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlarmSettingRepository alarmSettingRepository;

    private final SimpMessagingTemplate messagingTemplate;

    public AlarmService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    private boolean isAlarmEnabled(User user, AlarmType type) {
        return alarmSettingRepository.findByUser(user)
                .map(setting -> switch (type) {
                    case FOLLOW -> "true".equalsIgnoreCase(setting.getFollow());
                    case COMMENT -> "true".equalsIgnoreCase(setting.getComment());
                    case GREAT -> "true".equalsIgnoreCase(setting.getGreat());
                    case RECOMMENT -> "true".equalsIgnoreCase(setting.getRecomment());
                    case TODO -> "true".equalsIgnoreCase(setting.getTodo());
                    case WEATHER -> "true".equalsIgnoreCase(setting.getWeather());
                    default -> true;
                }).orElse(true);
    }

    private void send(String toUser, String message, AlarmType type, String icon) {
        User user = userRepository.findByUsername(toUser).orElseThrow();

        if (!isAlarmEnabled(user, type)) return;

        Alarm alarm = new Alarm();
        alarm.setUser(user);
        alarm.setAlarmType(type);
        alarm.setMessage(message);
        alarm.setAddDate(LocalDateTime.now());
        alarm.setRead(false);
        alarm.setIcon(icon);

        Alarm saved = alarmRepository.save(alarm);
        messagingTemplate.convertAndSendToUser(toUser, "/queue/private", saved.toAlarmDTO());
        System.out.println("✅ WebSocket 알림 전송 완료 → " + toUser);
    }

    // 🔔 기능별 알림
    public void sendFollowAlarm(String toUser, String fromUser) {
        if (!toUser.equals(fromUser)) {
            send(toUser, fromUser + "님이 당신을 팔로우했습니다.", AlarmType.FOLLOW, null);
        }
    }

    public void sendCommentAlarm(String toUser, String fromUser, String postTitle) {
        if (!toUser.equals(fromUser)) {
            send(toUser, fromUser + "님이 \"" + postTitle + "\"에 댓글을 남겼습니다.", AlarmType.COMMENT, null);
        }
    }

    public void sendReCommentAlarm(String toUser, String fromUser) {
        if (!toUser.equals(fromUser)) {
            send(toUser, fromUser + "님이 당신의 댓글에 답글을 남겼습니다.", AlarmType.RECOMMENT, null);
        }
    }

    public void sendGreatAlarm(String toUser, String fromUser) {
        if (!toUser.equals(fromUser)) {
            send(toUser, fromUser + "님이 게시글에 좋아요를 눌렀습니다.", AlarmType.GREAT, null);
        }
    }

    public void sendPlaceRecommendAlarmToAllUsers(String placeName) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            send(user.getUsername(), "추천 장소 '" + placeName + "'이(가) 새로 등록되었습니다.", AlarmType.RECOMMEND, null);
        }
    }

    // 📅 일정 등록 시: 기본 알림
    public void sendTodoAlarm(String toUser, String title) {
        send(toUser, "'" + title + "' 일정이 등록되었습니다.", AlarmType.TODO, null);
    }

    // 🌦️ 날씨 포함 알림 (스케줄러 등 전용)
    public void sendTodoWeatherAlarm(String toUser, String title, WeatherDTO weather) {
        if (weather == null) return;

        String message = "'" + title + "' 일정이 3일 후입니다! 🌦 예상 날씨: " +
                weather.getDescription() + ", " + weather.getTemp() + "도";

        Optional<Alarm> existing = alarmRepository.findFirstByUser_UsernameAndAlarmTypeAndMessageOrderByAddDateDesc(
                toUser, AlarmType.TODO, message
        );
        if (existing.isPresent()) {
            System.out.println("⚠️ 중복 일정 알림 생략: " + message);
            return;
        }

        send(toUser, message, AlarmType.TODO, weather.getIcon());
    }

    // 🌤 날씨 알림 (기념일, 일반 날씨)
    public void sendWeatherAlarm(String toUser, WeatherDTO weather, boolean isFuture) {
        if (weather == null) return;

        String message = "[예정된 날씨] " + weather.getDescription() + ", " + weather.getTemp() + "도";

        Optional<Alarm> existing = alarmRepository.findFirstByUser_UsernameAndAlarmTypeAndMessageOrderByAddDateDesc(
                toUser, AlarmType.WEATHER, message
        );
        if (existing.isPresent()) {
            System.out.println("❗ 중복 날씨 알림 있음 → 생략: " + message);
            return;
        }

        send(toUser, message, AlarmType.WEATHER, weather.getIcon());
    }

    // 📢 관리자 공지 알림
    public AlarmDTO saveAlarm() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        Alarm alarm = new Alarm();
        alarm.setUser(user);
        alarm.setAlarmType(AlarmType.EVENT);
        alarm.setMessage("관리자가 새로운 공지를 등록했습니다.");
        alarm.setAddDate(LocalDateTime.now());
        alarm.setRead(false);

        Alarm saved = alarmRepository.save(alarm);
        messagingTemplate.convertAndSendToUser(username, "/queue/private", saved.toAlarmDTO());

        return saved.toAlarmDTO();
    }

    public List<AlarmDTO> getAlarmsByUsername(String username) {
        List<Alarm> alarms = alarmRepository.findByUser_UsernameOrderByAddDateDesc(username);
        return alarms.stream().map(Alarm::toAlarmDTO).collect(Collectors.toList());
    }

    public boolean deleteAlarmById(Long alarmId, String username) {
        Optional<Alarm> optionalAlarm = alarmRepository.findById(alarmId);
        if (optionalAlarm.isEmpty()) return false;

        Alarm alarm = optionalAlarm.get();
        if (!alarm.getUser().getUsername().equals(username)) {
            return false; // 본인 알람만 삭제 가능
        }

        alarmRepository.delete(alarm);
        return true;
    }

}
