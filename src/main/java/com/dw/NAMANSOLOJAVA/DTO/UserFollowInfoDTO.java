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
    private List<AlbumDTO> albumList;

}
