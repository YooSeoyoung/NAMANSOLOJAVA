package com.dw.TheBoxer.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GreatDTO {
    private Long id;
    private Long albumId; // 유저
    private String username;
}
