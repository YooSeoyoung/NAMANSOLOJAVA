package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ai_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_name", nullable = false)
//    private User user;
//
//    @Column(name = "recommend_name", nullable = false)
//    private String recommendName; // 추천된 장소/코스 이름
//
//    @Column(name = "recommend_address")
//    private String recommendAddress; // 장소라면 주소
//
//    @Column(name = "category_name")
//    private String categoryName;  // 추천 카테고리
//
//    @Column(name = "recommend_type")
//    private String recommendType; // PLACE / COURSE / ACTIVITY 등
//
//    @Column(name = "tag_used")
//    private String tagUsed; // AI가 참고한 태그 정보
//
//    @Column(name = "ai_reason", length = 500)
//    private String aiReason; // AI의 추천 이유 텍스트
//
//    @Column(name = "recommend_time")
//    private LocalDateTime recommendTime;
}
