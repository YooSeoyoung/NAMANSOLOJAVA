package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class AnniversaryDTO {
    private Long id;
    private String title;
    private LocalDate startDate;
    private String color;
    private String type;
}
