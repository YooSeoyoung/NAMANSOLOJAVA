package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateAndFIndDTO {
    private String realNameM;
    private String realNameF;
    private String emailM;
    private String emailF;
    private String phoneNumberM;
    private String phoneNumberF;
    private String profileImageUrl;
    private LocalDate dDay;
    private String city;
}
