package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AlarmController {

    private final SimpMessagingTemplate messagingTemplate;

    public AlarmController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    // 1:1 알림 전송
    public void sendAlarmToUser(String username, AlarmDTO alarmDTO) {
        messagingTemplate.convertAndSendToUser(username, "/queue/private", alarmDTO);
    }
}
