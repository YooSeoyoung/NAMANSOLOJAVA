package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class BookmarkDTO {
    private Long albumId;
    private MediaDTO url;
    private  String username;
}
