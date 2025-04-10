package com.dw.NAMANSOLOJAVA.DTO;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class EventPresentDTO {

    private String title; // 선물 제목 (예: 커플 반지, 탁상 액자)

    private String description; // 요약 설명

    private String imageUrl; // 네이버 검색 이미지 썸네일

    private String shoppingUrl; // 실제 구매 링크 (네이버 쇼핑 결과)

    private String price; // 가격 정보 (문자열로 처리)
}
