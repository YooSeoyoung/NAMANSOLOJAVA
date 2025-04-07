package com.dw.TheBoxer.DTO;

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
    private LocalDateTime addTime;
    private String username;
    private String visibility;
    private List<CommentDTO> comments;
    private List<String> greats;
}
