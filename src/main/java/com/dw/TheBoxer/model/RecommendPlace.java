package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "recommend_place")
public class RecommendPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_name") // 맛집, 호텔, 관광지, 포토존, 카페
    private Category category;

    @Column(nullable = false)
    private String name; // 장소 이름

    @Column(nullable = false)
    private String address; // 주소 (지도 표시용)

    @Column(nullable = false)
    private Double latitude; // 위도

    @Column(nullable = false)
    private Double longitude; // 경도

    @Column(length = 1000)
    private String description; // 상세 설명 (선택)
}
