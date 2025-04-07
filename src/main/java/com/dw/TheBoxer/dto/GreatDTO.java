package com.dw.TheBoxer.dto;

import com.dw.TheBoxer.model.Album;
import com.dw.TheBoxer.model.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GreatDTO {
    private Long id;
    private Long albumId; // 유저
    private String username;
}
