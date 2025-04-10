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
import java.util.List;

@Service
public class AlarmService {
    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlarmController alarmController;

    public AlarmDTO saveAlarm() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        Alarm alarm = new Alarm();
        alarm.setUser(user);
        alarm.setAlarmType(AlarmType.EVENT);
        alarm.setMessage("📢 관리자가 새로운 공지를 등록했습니다.");
        alarm.setAddDate(LocalDateTime.now());
        alarm.setRead(false);

        Alarm saved = alarmRepository.save(alarm);


        alarmController.sendAlarmToUser(user.getUsername(), saved.toAlarmDTO());

        return saved.toAlarmDTO();
    }
}
