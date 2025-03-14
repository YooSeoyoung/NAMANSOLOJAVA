package com.dw.TheBoxer.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "detail")
public class Like { // 좋아요 기능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board; // 유저(단방향)

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user; // 유저(단방향)

    @Column(name = "feed_back")
    private Boolean feedBack; // true이면 좋아요 false이면 싫어요

    @Column(name = "is_active")
    private Boolean isActive=true; // true이면 화면 상에 보이게
}
