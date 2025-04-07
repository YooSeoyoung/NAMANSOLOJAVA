package com.dw.TheBoxer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ToDoTravelDTO {
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime lastDateTime;
    private String pictureURL; // 사진 경로
    private String type;
}
