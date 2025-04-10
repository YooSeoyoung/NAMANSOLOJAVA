package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RecommendPlaceAdmDTO { // 어드민 추가/수정용
    private Long id;
    private String name;
    private List<MediaDTO> mediaUrl;
    private String address;
    private String city;
    private Double latitude;
    private Double longitude;
    private String description;
    private String detail;
}
