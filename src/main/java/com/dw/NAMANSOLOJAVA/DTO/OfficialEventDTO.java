package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class OfficialEventDTO {
    private String eventType;
    private LocalDate eventDate;
}
