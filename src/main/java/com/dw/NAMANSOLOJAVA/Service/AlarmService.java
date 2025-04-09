package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.Repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmService {
    @Autowired
    AlarmRepository alarmRepository;

    public List<AlarmDTO> getAllAlarm() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return null;
    }

    public AlarmDTO updateAlarmById(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return null;
    }

    public List<AlarmDTO> saveAllAlarm() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return null;
    }

    public AlarmDTO saveAlarm() {


        return null;
    }
}
