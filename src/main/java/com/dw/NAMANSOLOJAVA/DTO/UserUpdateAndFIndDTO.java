package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;


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
    private String city;
}
