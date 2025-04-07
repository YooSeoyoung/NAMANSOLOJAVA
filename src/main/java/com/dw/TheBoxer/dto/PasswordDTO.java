package com.dw.TheBoxer.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PasswordDTO {

    private String username;

    private String email;

    private String newPassword;

    private String confirmNewPassword;
}
