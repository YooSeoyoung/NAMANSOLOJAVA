package com.dw.TheBoxer.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_name", nullable = false)
    private User follower; // 팔로우하는 사람

    @ManyToOne
    @JoinColumn(name = "following_name", nullable = false)
    private User following; // 팔로우 되는 사람

    @Column(name = "add_time", nullable = false)
    private LocalDateTime addTime; // 생성시간 지정 -> 유저 요청시간, 레포지토리에 매핑 LocalDateTime.now();

    @Column(name = "alert_sent", nullable = false)
    private Boolean alertSent; // 알람 발송 여부. 기본 false. 레포지토리에서 설정
}
