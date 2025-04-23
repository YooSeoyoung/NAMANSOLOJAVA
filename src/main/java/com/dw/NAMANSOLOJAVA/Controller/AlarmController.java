package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.Repository.AlarmRepository;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Service.AlarmService;
import com.dw.NAMANSOLOJAVA.model.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alarm")
public class AlarmController {

    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    AlarmService alarmService;

    private final SimpMessagingTemplate messagingTemplate;

    public AlarmController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    // 1:1 알림 전송
    public void sendAlarmToUser(String username, AlarmDTO alarmDTO) {
        messagingTemplate.convertAndSendToUser(username, "/queue/private", alarmDTO);
    }

    @GetMapping("/user/{username}")
    public List<AlarmDTO> getUserAlarms(@PathVariable String username) {
        return alarmService.getAlarmsByUsername(username);
    }

    @GetMapping("/me")
    public List<AlarmDTO> getCurrentUserAlarms() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return alarmService.getAlarmsByUsername(username);
    }
}
