package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="is_active")
    private Boolean isActive = true; // 게시판 삭제 시, false로 변경

    @Column(name = "title", nullable = false)
    private String title; // 제목 , 본문( 복싱화, 샌드백, 복싱글러브(온즈별),질문 제목)


    @Column(name = "content", nullable = false)
    private String content; // 설명

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user; //하나의 유저가 많은 Board를 가질 수 있음

    @Column(name = "add_date", updatable = false)
    private LocalDate addDate; // 작성일

    @Column(name = "modified_date")
    private LocalDate modifiedDate; // 수정일

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category; // 카테고리 (단방향)

    @OneToMany(mappedBy = "board") // 댓글 모음
    private List<Comment> commentList= new ArrayList<>();

    @OneToMany(mappedBy = "board") // 좋아요 모음
    private List<Great> greatList = new ArrayList<>();

    @Column(name = "count_like")
    private Long countLike; // 좋아요 수

    @Column(name = "count_dislike")
    private Long countDisLike; // 싫어요 수
}
