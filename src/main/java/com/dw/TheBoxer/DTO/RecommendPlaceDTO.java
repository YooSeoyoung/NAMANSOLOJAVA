package com.dw.TheBoxer.DTO;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class RecommendPlaceDTO {
    private String name;
    private List<PictureAndVideoDTO> picVidDTO;
    private String address;
    private String city;
    private String description;
    private String detail;
}
