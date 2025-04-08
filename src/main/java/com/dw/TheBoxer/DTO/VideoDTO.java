package com.dw.TheBoxer.DTO;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class VideoDTO {
    private Long todoId;
    private Long recommendPlaceId;
    private Long albumId;
    private String videoURL;
}
