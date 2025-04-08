package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.AlbumTagDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "story_tag")
public class AlbumTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public AlbumTagDTO toDTO(){
        return new AlbumTagDTO(
                this.id,this.album.getId(),this.tag.getName()
        );
    }
}
