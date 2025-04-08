package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateDTO {
    private String gender;
    private String realName;
    private String email;
    private String phoneNumber;
}
