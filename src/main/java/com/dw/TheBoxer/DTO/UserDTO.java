package com.dw.TheBoxer.DTO;


import com.dw.TheBoxer.enums.Gender;
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
    private String gender;
    private String emailM;
    private String emailF;
    private LocalDate birthM;
    private LocalDate birthF;
    private String phoneNumberM;
    private String phoneNumberF;
    private String authority;
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
    private PictureAndVideoDTO mediaUrl;
}
