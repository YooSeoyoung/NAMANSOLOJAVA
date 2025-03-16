package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "friend_request")
public class FriendRequest { // 친구요청 및 받는 클래스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_name")
    private User sender;  // 하나의 유저가 많은 요청이 가능함

    @ManyToOne
    @JoinColumn(name = "receiver_name")
    private User receiver; //하나의 유저가 많은 요청을 받을 수 있음

    private Boolean approved = false;  // 기본적으로 요청은 승인 X
}
