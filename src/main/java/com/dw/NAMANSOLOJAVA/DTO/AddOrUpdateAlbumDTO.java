package com.dw.NAMANSOLOJAVA.DTO;


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
    private Double latitude;
    private Double longitude;
    private String location;
}
