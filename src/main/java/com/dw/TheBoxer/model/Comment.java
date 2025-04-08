package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.AddOrUpdateCommentDTO;
import com.dw.TheBoxer.DTO.CommentDTO;
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

    @Column(name = "add_date")
    private LocalDateTime addDate; // 작성시간

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public AddOrUpdateCommentDTO toAddOrUpdateCommentDTO(){
        return  new AddOrUpdateCommentDTO(
                this.content, this.addDate, this.album.getId()
        );
    }

    public CommentDTO toCommentDTO(){
        return new CommentDTO(
                this.id, this.content,
                this.addDate, this.album.getId(),
                this.user.getUsername()
        );
    }


}
