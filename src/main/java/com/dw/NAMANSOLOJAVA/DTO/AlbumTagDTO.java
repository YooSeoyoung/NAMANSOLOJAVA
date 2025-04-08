package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AlbumTagDTO {

    private Long id;
    private Long albumId;
    private String tagName;
}
