package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "user_recommend")
public class UserRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recommend_place")
    private RecommendPlace recommendPlace;

    @Column(name = "recommend_alert")
    private Boolean recommendAlert;
}
