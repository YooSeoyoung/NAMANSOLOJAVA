package com.dw.NAMANSOLOJAVA.DTO;


import com.dw.NAMANSOLOJAVA.model.AlbumTag;
import com.dw.NAMANSOLOJAVA.model.Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateAlbumDTO {
    private Long id;
    private String title;
    private String visibility;
    private List<MediaDTO> mediaUrl;
    private Double latitude;
    private Double longitude;
    private String location;
    private List<Tag> taglist =new ArrayList<>();
}
