package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Controller.AlarmController;
import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.Repository.AlarmRepository;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import com.dw.NAMANSOLOJAVA.model.Alarm;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlarmController alarmController;

    // toUser에게 message 내용으로 type 알림
    private void send(String toUser, String message, AlarmType type) {
        User user = userRepository.findByUsername(toUser).orElseThrow();

        Alarm alarm = new Alarm();
        alarm.setUser(user);
        alarm.setAlarmType(type);
        alarm.setMessage(message);
        alarm.setAddDate(LocalDateTime.now());
        alarm.setRead(false);

        Alarm saved = alarmRepository.save(alarm);
        alarmController.sendAlarmToUser(toUser, saved.toAlarmDTO());
    }

    // 관리자가 직접 이벤트 등록 시 유저에게 알림
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
        alarmController.sendAlarmToUser(user.getUsername(), saved.toAlarmDTO());

        return saved.toAlarmDTO();
    }
    // 1:1 알림으로 팔로우 소식 알림
    public void sendFollowAlarm(String toUser, String fromUser) {
        send(toUser,fromUser + "님이 당신을 팔로우했습니다.", AlarmType.FOLLOW);
    }
    // 내가 작성한 게시물에 댓글이 달렸을때 알림
    public void sendCommentAlarm(String toUser, String fromUser, String postTitle) {
        send(toUser, fromUser + "님이 \"" + postTitle + "\"에 댓글을 남겼습니다.", AlarmType.COMMENT);
    }
    // 내가 남긴 댓글에 대댓글이 달렸을때 알림
    public void sendReCommentAlarm(String toUser, String fromUser) {
        send(toUser, fromUser + "님이 당신의 댓글에 답글을 남겼습니다.", AlarmType.RECOMMENT);
    }
    // 내 게시물을 좋아요 누르면 알림
    public void sendGreatAlarm(String toUser, String fromUser) {
        send(toUser,fromUser + "님이 게시글에 좋아요를 눌렀습니다.", AlarmType.GREAT);
    }
    // 추가한 장소가 있으면 유저에게 알림
    public void sendPlaceRecommendAlarm(String toUser, String placeName) {
        send(toUser, "추천 장소 '" + placeName + "'이(가) 새로 등록되었습니다.", AlarmType.RECOMMEND);
    }
    // 기념일이나 일정에 관해 알림
    public void sendTodoAlarm(String toUser, String title) {
        send(toUser, "'" + title + "' 일정이 다가오고 있어요", AlarmType.TODO);
    }
    // 날씨 정보 알림 (오늘 or 미래 구분)
    public void sendWeatherAlarm(String toUser, String summary, boolean isFuture) {
        String prefix = isFuture ? "7일 후 기념일의 예상 날씨: " : "오늘의 날씨: ";
        send(toUser, prefix + summary, AlarmType.WEATHER);
    }
}