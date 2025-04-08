package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.AlbumTagDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "album_tag")
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
