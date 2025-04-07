package com.dw.TheBoxer.DTO;

import com.dw.TheBoxer.model.Comment;
import com.dw.TheBoxer.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReCommentDTO {
    private Long id;

    private String content;

    private LocalDateTime addDate;

    private Long commentId;

    private String username;

}
