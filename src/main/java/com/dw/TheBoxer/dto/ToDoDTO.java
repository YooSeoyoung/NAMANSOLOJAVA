package com.dw.TheBoxer.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ToDoDTO {
    private String title;
    private LocalDate startDate;
    private String type;
}
