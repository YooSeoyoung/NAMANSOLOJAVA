package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.PictureAndVideoDTO;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
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

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "media_type")
    private MediaType mediaType;

    public MediaDTO toDTO(){
        return new MediaDTO(
                this.id, this.mediaUrl,
                this.mediaType.name()
        );
    }
}
