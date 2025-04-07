package com.dw.TheBoxer.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddOrUpdateAlbumDTO {
    private String title;
    private String visibility;
}
