package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.Service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarm")
public class AlarmController {
    @Autowired
    AlarmService alarmService;

    @GetMapping("/all")
    public ResponseEntity<List<AlarmDTO>> getAllAlarm() {
        return new ResponseEntity<>(alarmService.getAllAlarm(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlarmDTO> updateAlarmById(@PathVariable Long id) {
        return new ResponseEntity<>(alarmService.updateAlarmById(id), HttpStatus.OK);
    }

    @PostMapping("/save/all")
    public ResponseEntity<List<AlarmDTO>> saveAllAlarm() {
        return new ResponseEntity<>(alarmService.saveAllAlarm(), HttpStatus.OK);
    }

    @PostMapping("/save/single")
    public ResponseEntity<AlarmDTO> saveSingleAlarm() {
        return new ResponseEntity<>(alarmService.saveAlarm(), HttpStatus.OK);
    }
}
