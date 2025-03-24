package com.dw.TheBoxer.model;

import com.dw.TheBoxer.enums.Rating;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "add_time", updatable = false)
    private LocalDateTime addDate; // 작성시간

    @Column(name = "modified_time")
    private LocalDateTime modifiedTime; // 수정시간

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;
}
