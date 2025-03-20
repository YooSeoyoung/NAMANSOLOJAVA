package com.dw.TheBoxer.model;

import com.dw.TheBoxer.enums.Rating;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "comment")
public class Comment { // 댓글 기능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "add_date", updatable = false)
    private LocalDate addDate; // 작성일

    @Column(name = "modified_date")
    private LocalDate modifiedDate; // 수정일

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @Column(name = "rating",nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating rating; // ENUM 수정 필요
}
