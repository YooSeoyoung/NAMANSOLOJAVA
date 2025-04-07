package com.dw.TheBoxer.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class RecommendPlaceDTO {
    private String name;
    private String address;
    private String city;
    private String description;
    private String detail;
}
