package com.dw.TheBoxer.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "friendship")
public class FriendShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 친구 관계의 한쪽 유저

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;  // 친구 관계의 다른 유저
}
