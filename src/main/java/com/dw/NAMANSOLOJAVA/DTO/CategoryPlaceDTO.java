package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CategoryPlaceDTO {
    private CategoryDTO category;
    private Long recommendPlaceId;
}
