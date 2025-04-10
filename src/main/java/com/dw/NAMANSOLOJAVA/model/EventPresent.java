package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.EventPresentDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "event_present")
public class EventPresent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; // 선물 제목 (예: 커플 반지, 탁상 액자)

    @Column(name = "description", length = 1000)
    private String description; // 요약 설명

    @Column(name = "image_url")
    private String imageUrl; // 네이버 검색 이미지 썸네일

    @Column(name = "shopping_url")
    private String shoppingUrl; // 실제 구매 링크 (네이버 쇼핑 결과)

    @Column(name = "price")
    private String price; // 가격 정보 (문자열로 처리)

    public EventPresentDTO toDTO() {
        return new EventPresentDTO(this.title, this.description, this.imageUrl, this.shoppingUrl, this.price);
    }
}
