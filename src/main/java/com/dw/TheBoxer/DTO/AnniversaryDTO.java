package com.dw.TheBoxer.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class AnniversaryDTO {
    private String title;
    private LocalDate startDate;
    private String type;
}
