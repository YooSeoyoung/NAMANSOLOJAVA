package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MonthlyUserAlbumCountDTO {
    private int month;
    private  String username;
    private Long count;
}
