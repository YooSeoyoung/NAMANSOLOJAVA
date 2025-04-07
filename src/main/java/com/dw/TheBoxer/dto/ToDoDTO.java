package com.dw.TheBoxer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ToDoDTO {
    private String title;
    private LocalDateTime startDateTime;
    private String type;
}
