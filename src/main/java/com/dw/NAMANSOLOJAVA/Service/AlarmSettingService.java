package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AlarmSettingDTO;
import com.dw.NAMANSOLOJAVA.Repository.AlarmSettingRepository;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.model.AlarmSetting;
import com.dw.NAMANSOLOJAVA.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmSettingService {

    private final UserRepository userRepository;
    private final AlarmSettingRepository settingRepository;

    public AlarmSetting getSetting(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return settingRepository.findByUser(user)
                .orElseGet(() -> {
                    AlarmSetting defaultSetting = new AlarmSetting();
                    defaultSetting.setUser(user);
                    return settingRepository.save(defaultSetting);
                });
    }

    public void updateSetting(String username, AlarmSettingDTO dto) {
        User user = userRepository.findByUsername(username).orElseThrow();

        AlarmSetting setting = settingRepository.findByUser(user)
                .orElseGet(() -> {
                    AlarmSetting s = new AlarmSetting();
                    s.setUser(user);
                    return s;
                });

        setting.setFollow(dto.isFollow());
        setting.setComment(dto.isComment());
        setting.setGreat(dto.isGreat());
        setting.setRecomment(dto.isRecomment());
        setting.setTodo(dto.isTodo());
        setting.setWeather(dto.isWeather());

        settingRepository.save(setting);
    }
}
