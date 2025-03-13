package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.stream.events.Comment;
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

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user; // 유저(단방향)

    @Column(name = "add_date", updatable = false)
    private LocalDate addDate; // 작성일

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category; // 카테고리 (단방향)

    @OneToMany(mappedBy = "board")
    private List<Comment> commentList= new ArrayList<>();


}
