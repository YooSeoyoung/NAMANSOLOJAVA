package com.dw.TheBoxer.DTO;

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
