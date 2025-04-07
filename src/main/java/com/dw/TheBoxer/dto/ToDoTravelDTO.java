package com.dw.TheBoxer.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ToDoTravelDTO {
    private String title;
    private LocalDate startDate;
    private LocalDate lastDate;
    private String pictureURL; // 사진 경로
    private String type;
}
