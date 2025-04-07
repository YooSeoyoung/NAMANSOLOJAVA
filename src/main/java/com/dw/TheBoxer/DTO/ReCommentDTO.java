package com.dw.TheBoxer.DTO;

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
