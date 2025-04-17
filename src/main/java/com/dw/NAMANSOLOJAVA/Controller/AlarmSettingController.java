package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AlarmSettingDTO;
import com.dw.NAMANSOLOJAVA.Service.AlarmSettingService;
import com.dw.NAMANSOLOJAVA.model.AlarmSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alarm/setting")
@RequiredArgsConstructor
public class AlarmSettingController {

    private final AlarmSettingService settingService;

    @GetMapping
    public ResponseEntity<AlarmSetting> getUserSetting() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(settingService.getSetting(username));
    }

    @PutMapping
    public ResponseEntity<Void> updateUserSetting(@RequestBody AlarmSettingDTO settingDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        settingService.updateSetting(username, settingDTO);
        return ResponseEntity.ok().build();
    }
}