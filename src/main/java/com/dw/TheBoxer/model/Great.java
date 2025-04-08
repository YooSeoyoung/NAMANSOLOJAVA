package com.dw.TheBoxer.model;


import com.dw.TheBoxer.DTO.GreatDTO;
import com.dw.TheBoxer.enums.Reaction;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "great")
public class Great { // 좋아요 기능
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album; // 유저

    @ManyToOne
    @JoinColumn(name = "username")
    private User user; // 유저
//
//    @Column(name = "reaction")
//    @Enumerated(EnumType.STRING)
//    private Reaction reaction; // ENUM

    public GreatDTO toDTO(){
        return new GreatDTO(
                this.id,
                this.album.getId(),
                this.user.getUsername()
        );
    }
}
