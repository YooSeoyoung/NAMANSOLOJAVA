package com.dw.NAMANSOLOJAVA.DTO;


import com.dw.NAMANSOLOJAVA.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String realNameM;
    private String realNameF;
    private String emailM;
    private String emailF;
    private LocalDate birthM;
    private LocalDate birthF;
    private String phoneNumberM;
    private String phoneNumberF;
    private String authority;
    private String city;
    private LocalDate addDate;
    private LocalDate dDay;
    private Boolean alarmAlert;
    private Boolean commentAlert;
    private Boolean followAlert;
    private Boolean greatAlert;
    private Boolean eventAlert;
    private Boolean recommendAlert;
    private Boolean recommentAlert;
    private Boolean todoAlert;
    private MediaDTO mediaDTO;
    private LocalDate lastLogin;
}