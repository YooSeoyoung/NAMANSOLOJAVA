package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserFollowInfoDTO {
    private String username;
    private String profileUrl;

    // 서로 맞팔 상태인지 여부
    private boolean isMutualFollow;
}
