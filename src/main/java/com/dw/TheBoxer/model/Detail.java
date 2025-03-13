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
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title; // 제목 , 본문( 복싱화, 샌드백, 복싱글러브(온즈별),질문 제목)


    @Column(name = "description",nullable = false)
    private String description; // 설명

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category; // 카테고리를 선택하여 작성
}
