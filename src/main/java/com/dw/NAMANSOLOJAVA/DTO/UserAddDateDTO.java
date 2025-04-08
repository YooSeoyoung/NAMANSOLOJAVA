package com.dw.NAMANSOLOJAVA.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAddDateDTO {
    private String username;
    private LocalDate addDate;
}
