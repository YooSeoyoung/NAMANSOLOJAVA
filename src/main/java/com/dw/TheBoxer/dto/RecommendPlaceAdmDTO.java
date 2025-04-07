package com.dw.TheBoxer.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class RecommendPlaceAdmDTO { // 어드민 추가용 + 지도 상에 위치를 주는 용도
    private String categoryName;
    private String placeName;
    private String address;
    private String city;
    private Double latitude;
    private Double longitude;
    private String description;
    private String detail;
}
