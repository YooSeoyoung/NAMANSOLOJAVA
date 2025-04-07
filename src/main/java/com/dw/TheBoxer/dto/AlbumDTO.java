package com.dw.TheBoxer.dto;

import com.dw.TheBoxer.enums.Visibility;
import com.dw.TheBoxer.model.Comment;
import com.dw.TheBoxer.model.Great;
import com.dw.TheBoxer.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
