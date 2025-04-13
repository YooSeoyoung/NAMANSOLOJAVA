package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class BookmarkDTO {
    private Long albumId;
    private String url;
    private  String username;
}
