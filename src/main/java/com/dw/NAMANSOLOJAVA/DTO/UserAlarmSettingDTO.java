package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAlarmSettingDTO {
    private Boolean alarmAlert;
    private Boolean commentAlert;
    private Boolean followAlert;
    private Boolean greatAlert;
    private Boolean eventAlert;
    private Boolean recommendAlert;
    private Boolean recommentAlert;
    private Boolean todoAlert;
}
