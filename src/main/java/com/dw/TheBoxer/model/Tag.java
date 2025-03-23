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
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "feed_tag",
            joinColumns = @JoinColumn(name = "tag_name"),
            inverseJoinColumns = @JoinColumn(name = "feed_id"))  // 여러 태그는 여러 게시글을 가질 수 있음
    private List<Feed> feeds;  // 해당 태그가 포함된 게시글 목록

    @OneToMany(mappedBy = "tag")
    private List<UserTagHistory> userTagHistories;  // 이 태그를 사용한 유저들의 사용 이력
}
