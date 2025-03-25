package com.dw.TheBoxer.model;


import com.dw.TheBoxer.enums.Reaction;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "great")
public class Great { // 좋아요 기능
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story; // 유저

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user; // 유저

    @Column(name = "reaction")
    @Enumerated(EnumType.STRING)
    private Reaction reaction; // ENUM

    @Column(name = "alert_sent", nullable = false)
    private Boolean alertSent; // 알람 발송 여부. 기본 false. 레포지토리에서 설정
}
