package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AlbumDTO {
    private Long id;
    private String title;
    private LocalDateTime addDate;
    private String username;
    private String visibility;
    private List<String> userTags;
    private List<CommentDTO> comments;
    private List<String> greats;
    private List<MediaDTO> url;
    private String location;
}
