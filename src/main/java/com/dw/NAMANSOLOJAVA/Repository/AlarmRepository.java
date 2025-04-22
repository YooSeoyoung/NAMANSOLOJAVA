package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import com.dw.NAMANSOLOJAVA.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findByUser_UsernameOrderByAddDateDesc(String username);

    Optional<Alarm> findFirstByUser_UsernameAndAlarmTypeAndMessageOrderByAddDateDesc(String username, AlarmType type, String message);

}
