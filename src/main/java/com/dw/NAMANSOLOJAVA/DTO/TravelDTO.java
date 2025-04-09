package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class TravelDTO {
    private String title;
    private LocalDate startDate;
    private LocalDate lastDate;
    private List<MediaDTO> mediaUrl; // 사진 경로
    private String type;
}
