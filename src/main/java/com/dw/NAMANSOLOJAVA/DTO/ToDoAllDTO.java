package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ToDoAllDTO {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<MediaDTO> mediaUrl; // 사진 경로
    private String color;
    private String type;
    private Boolean editable;
}
