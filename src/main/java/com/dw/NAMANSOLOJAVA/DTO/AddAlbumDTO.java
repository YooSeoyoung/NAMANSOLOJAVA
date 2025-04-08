package com.dw.NAMANSOLOJAVA.DTO;

import com.dw.NAMANSOLOJAVA.model.Tag;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddAlbumDTO {
    private String title;
    private String visibility;
    private List<PictureAndVideoDTO> mediaUrl;
    private List<Tag> tagList;
    private Double latitude;
    private Double longitude;
    private String location;
}
