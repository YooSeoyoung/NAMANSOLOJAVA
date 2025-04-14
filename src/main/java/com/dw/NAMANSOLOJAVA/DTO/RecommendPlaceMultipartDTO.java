package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecommendPlaceMultipartDTO {
    private String name;
    private String address;
    private String description;
    private String category;
    private String city;
    private Double latitude;
    private Double longitude;
    private MultipartFile file;
}
