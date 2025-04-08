package com.dw.NAMANSOLOJAVA.DTO;

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
    private String mediaUrl;
    private String mediaType;
}
