package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class OfficialEventDTO {
    private LocalDate eventDate;
    private String eventTitle;
}
