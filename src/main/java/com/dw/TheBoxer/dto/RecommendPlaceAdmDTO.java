package com.dw.TheBoxer.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class RecommendPlaceAdmDTO { // 어드민 추가용
    private String name;
    private String address;
    private String city;
    private Double latitude;
    private Double longitude;
    private String description;
    private String detail;
}
