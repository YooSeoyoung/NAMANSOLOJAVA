package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ToDoTravelDTO {
    private String title;
    private LocalDate startDate;
    private LocalDate lastDate;
    private List<PictureAndVideoDTO> mediaUrl; // 사진 경로
    private String type;
}
