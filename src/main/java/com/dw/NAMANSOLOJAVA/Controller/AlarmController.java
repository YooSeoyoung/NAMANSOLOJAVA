package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.Repository.AlarmRepository;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Service.AlarmService;
import com.dw.NAMANSOLOJAVA.model.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{alarmId}")
    public ResponseEntity<?> deleteAlarm(@PathVariable Long alarmId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean deleted = alarmService.deleteAlarmById(alarmId, username);
        if (deleted) {
            return ResponseEntity.ok("알람 삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한 없음 또는 존재하지 않는 알람");
        }
    }

}
