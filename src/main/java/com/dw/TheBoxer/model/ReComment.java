package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.AddOrUpdateReCommentDTO;
import com.dw.TheBoxer.DTO.ReCommentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "recomment")
public class ReComment {  // 대댓글 기능
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "add_date")
    private LocalDateTime addDate; // 추가시간


    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    public AddOrUpdateReCommentDTO toAddOrUpdateReCommentDTO(){
        return new AddOrUpdateReCommentDTO(
                this.content, this.addDate,
                this.comment.getId()
        );
    }
    public ReCommentDTO toRecommentDTO(){
        return new ReCommentDTO(
                this.id, this.content,
                this.addDate, this.comment.getId(),
                this.user.getUsername()
        );
    }

}
