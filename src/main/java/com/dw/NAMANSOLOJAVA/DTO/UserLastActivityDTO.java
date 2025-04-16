package com.dw.NAMANSOLOJAVA.DTO;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserLastActivityDTO {
    private String username;
    private LocalDate lastLogin;
}
