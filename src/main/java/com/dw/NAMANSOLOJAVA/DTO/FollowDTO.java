package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FollowDTO {
    private Long id;
    private String follower; // 팔로우하는 사람
    private String following; // 팔로우 되는 사람
}
