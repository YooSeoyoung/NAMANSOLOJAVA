package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tag")
public class Tag {


    @Id
    private String name;  // 태그 이름 (예: '복싱화', '샌드백', '루틴', '식단' 등 관리자가  고정)

    @OneToMany(mappedBy = "tag")  // 하나의 태그는 여러 게시글을 가질 수 있음
    private List<Board> boards = new ArrayList<>();  // 해당 태그가 포함된 게시글 목록


}
