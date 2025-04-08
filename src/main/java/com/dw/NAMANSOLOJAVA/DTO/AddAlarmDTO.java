package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddAlarmDTO {
    private String username;
    private String alarmType;
    private String message;
    private String weatherInfo; // EVENT이면 사용 없으면 "" 빈문자열로 처리
}
