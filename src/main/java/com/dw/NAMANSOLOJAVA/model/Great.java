package com.dw.NAMANSOLOJAVA.model;


import com.dw.NAMANSOLOJAVA.DTO.GreatDTO;
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



    public GreatDTO toDTO(){
        return new GreatDTO(
                this.id,
                this.album.getId(),
                this.user.getUsername()
        );
    }
}
