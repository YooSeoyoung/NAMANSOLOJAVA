package com.dw.NAMANSOLOJAVA.DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRelationDTO {
    private String username;
    private String profileUrl;
    private String relation;// FOLLOWING, FOLLOWER, FRIEND, NONE
}
