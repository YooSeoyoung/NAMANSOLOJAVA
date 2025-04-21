package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CategoryPlaceDTO {
    private Long recommendPlaceId;
    private List<String> categoryIds;
}