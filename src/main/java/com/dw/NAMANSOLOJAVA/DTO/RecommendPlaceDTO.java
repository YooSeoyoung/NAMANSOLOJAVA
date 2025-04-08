package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class RecommendPlaceDTO {
    private String name;
    private List<PictureAndVideoDTO> mediaUrl;
    private String address;
    private String city;
    private String description;
    private String detail;
}
