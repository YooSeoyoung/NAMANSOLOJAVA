package com.dw.TheBoxer.DTO;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddOrUpdateAlbumDTO {
    private String title;
    private String visibility;
    private List<PictureAndVideoDTO> mediaUrl;

}
