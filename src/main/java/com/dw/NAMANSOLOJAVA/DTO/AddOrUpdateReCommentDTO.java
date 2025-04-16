package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddOrUpdateReCommentDTO {
    private Long id;
    private String content;
    private LocalDateTime addDate;
    private Long commentId;
    private String username;
}
