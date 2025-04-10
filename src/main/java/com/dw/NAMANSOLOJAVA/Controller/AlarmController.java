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

    public void sendAlarmToUser(String username, AlarmDTO alarmDTO) {
        messagingTemplate.convertAndSendToUser(username, "/queue/alarm", alarmDTO);
    }

    @MessageMapping("/alarm.send")
    @SendTo("/topic/alarm")
    public AlarmDTO receiveFromClient(@Payload AlarmDTO alarmDTO) {
        System.out.println(" 알림 메시지 수신: " + alarmDTO.getMessage());
        return alarmDTO;
    }
}
