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

    public void sendFollowAlarm(String toUser, String fromUser) {
        send(toUser,fromUser + "님이 당신을 팔로우했습니다.", AlarmType.FOLLOW);
    }

    public void sendAlbumAlarm(String toUser, String albumTitle) {
        send(toUser, "새 앨범 \"" + albumTitle + "\"이(가) 등록되었습니다.", AlarmType.ALBUM);
    }

    public void sendCommentAlarm(String toUser, String fromUser, String postTitle) {
        send(toUser, fromUser + "님이 \"" + postTitle + "\"에 댓글을 남겼습니다.", AlarmType.COMMENT);
    }

    public void sendGreatAlarm(String toUser, String fromUser) {
        send(toUser,fromUser + "님이 게시글에 좋아요를 눌렀습니다.", AlarmType.GREAT);
    }

    public void sendEventAlarm(String toUser, String eventTitle) {
        send(toUser, "새로운 이벤트 \"" + eventTitle + "\"이(가) 시작되었습니다.", AlarmType.EVENT);
    }

    public void sendPlaceRecommendAlarm(String toUser, String placeName) {
        send(toUser, "오늘의 추천 장소: " + placeName, AlarmType.RECOMMEND);
    }

    public void sendReCommentAlarm(String toUser, String fromUser) {
        send(toUser, fromUser + "님이 당신의 댓글에 답글을 남겼습니다.", AlarmType.RECOMMENT);
    }

    public void sendTodoAlarm(String toUser, String todoTitle) {
        send(toUser, "해야 할 일: " + todoTitle + " 잊지 마세요!", AlarmType.TODO);
    }

    public void sendWeatherAlarm(String toUser, String summary) {
        send(toUser, "오늘의 날씨: " + summary, AlarmType.WEATHER);
    }
}