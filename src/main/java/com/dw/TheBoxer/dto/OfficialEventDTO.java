package com.dw.TheBoxer.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class OfficialEventDTO {
    private String eventTitle;
    private LocalDate eventDate;
}
