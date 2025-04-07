package com.dw.TheBoxer.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class RecommendPlaceDTO { // 유저 조회용
    private String categoryName;
    private String placeName;
    private String address;
    private String city;
    private String description;
    private String detail;
}
