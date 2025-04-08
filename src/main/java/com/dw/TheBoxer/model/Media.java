package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.MediaDTO;
import com.dw.TheBoxer.enums.MediaType;
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

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "media_type")
    private MediaType mediaType;

    public MediaDTO toDTO() {
        if (!toDo.equals(new ToDo())) {
            return new MediaDTO(toDo.getId(), null,
                    null, mediaUrl,
                    mediaType.name());
        } else if (!recommendPlace.equals((new RecommendPlace()))) {
            return new MediaDTO(null, recommendPlace.getId(),
                    null, mediaUrl,
                    mediaType.name());
        } else {
            return new MediaDTO(null, null,
                    album.getId(), mediaUrl,
                    mediaType.name());
        }
    }
}
