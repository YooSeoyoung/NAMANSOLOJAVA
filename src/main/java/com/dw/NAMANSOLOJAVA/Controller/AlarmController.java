package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AlarmController {

    private final SimpMessagingTemplate messagingTemplate;

    public AlarmController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    // 1:1 알림 전송
    // 예: 댓글, 좋아요 등 특정 유저에게 알림 보내기
    public void sendAlarmToUser(String username, AlarmDTO alarmDTO) {
        messagingTemplate.convertAndSendToUser(username, "/queue/alarm", alarmDTO);
    }
    // 전체 유저에게 알림 브로드캐스트 (관리자 공지/이벤트 등)
    // 클라이언트가 /app/alarm.send 로 메시지 전송 → /topic/alarm 구독자에게 전달됨
    @MessageMapping("/alarm.send")
    @SendTo("/topic/alarm")
    public AlarmDTO receiveFromClient(@Payload AlarmDTO alarmDTO) {
        System.out.println(" 알림 메시지: " + alarmDTO.getMessage());
        return alarmDTO;
    }
}
