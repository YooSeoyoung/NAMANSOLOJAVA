package com.dw.TheBoxer.dto;

import com.dw.TheBoxer.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FollowerDTO {
    private Long id;
    private String followerName;

}
