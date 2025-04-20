package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.AlarmSetting;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlarmSettingRepository extends JpaRepository<AlarmSetting, Long> {
    Optional<AlarmSetting> findByUser(User user);
}
