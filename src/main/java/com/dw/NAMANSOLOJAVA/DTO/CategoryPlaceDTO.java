package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class CategoryPlaceDTO {
    private CategoryDTO categoryName;
    private Long recommendId;
}
