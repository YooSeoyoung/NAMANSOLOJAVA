package com.dw.TheBoxer.DTO;

import com.dw.TheBoxer.enums.AlarmType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddAlarmDTO {
    private String user;
    private String alarmType;
    private String message;
    private String weatherInfo; // EVENT이면 사용 없으면 "" 빈문자열로 처리
}
