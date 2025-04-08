package com.dw.NAMANSOLOJAVA.DTO;


import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddOrUpdateCommentDTO {
    private String content;
    private LocalDateTime addDate;
    private Long albumId;
}
