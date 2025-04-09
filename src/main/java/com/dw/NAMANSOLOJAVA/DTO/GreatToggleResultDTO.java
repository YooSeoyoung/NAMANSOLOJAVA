package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GreatToggleResultDTO {
    private Long albumId;
    private Boolean liked;
    private String username;
}
