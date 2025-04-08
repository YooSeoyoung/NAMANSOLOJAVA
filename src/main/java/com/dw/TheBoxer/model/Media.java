package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private ToDo toDo;

    @ManyToOne
    @JoinColumn(name = "recommend_place_id")
    private RecommendPlace recommendPlace;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "video_url")
    private String videoUrl;
}
