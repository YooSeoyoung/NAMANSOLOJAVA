package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class OfficialEventDTO {
    private Long id;
    private LocalDate eventDate;
    private String eventTitle;
    private Long offsetDays = 0L;
}
