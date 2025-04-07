package com.dw.TheBoxer.DTO;

import com.dw.TheBoxer.enums.AlarmType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddAlarmDTO {
    private String user;
    private AlarmType alarmType;
    private String message;
    private String weatherInfo; // EVENT이면 사용 없으면 "" 빈문자열로 처리
}
