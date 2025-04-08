package com.dw.TheBoxer.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MediaDTO {
    private Long todoId;
    private Long recommendPlaceId;
    private Long albumId;
    private String pictureURL;
    private String VideoURL;
}
