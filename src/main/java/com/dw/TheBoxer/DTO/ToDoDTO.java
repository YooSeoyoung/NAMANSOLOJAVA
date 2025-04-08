package com.dw.TheBoxer.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ToDoDTO {
    private String title;
    private LocalDate startDate;
    private List<MediaDTO> medias;
    private String type;
}
