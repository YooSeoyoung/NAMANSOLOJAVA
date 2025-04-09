package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.CommentDTO;
import com.dw.NAMANSOLOJAVA.Exception.PermissionDeniedException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Repository.CommentRepository;
import com.dw.NAMANSOLOJAVA.Repository.RecommentRepository;
import com.dw.NAMANSOLOJAVA.model.Comment;
import com.dw.NAMANSOLOJAVA.model.Tag;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RecommentRepository recommentRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    UserService userService;

    public CommentDTO getCommentById(Long id){

        return  commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("존재하지 않은  ID입니다")).toCommentDTO();
    }

    public List<CommentDTO> getCommentByAlbumId(Long albumId){
        return  commentRepository.findByAlbumId(albumId).stream().map(Comment::toCommentDTO).toList();
    }
    @Transactional
    public String deleteCommentById(Long id){

     User user= userService.getCurrentUser();
     Comment comment = commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("존재하지 않은 댓글 Id입니다"));
     if (!comment.getUser().equals(user)){
         throw new PermissionDeniedException("본인의 댓글에 대해서만 삭제가 가능합니다");
     }
        recommentRepository.deleteAllByCommentId(id);
        commentRepository.deleteById(id);


        return  "댓글이 정상 삭제되었습니다";
    }

    public AddOrUpdateCommentDTO updateComment( AddOrUpdateCommentDTO addOrUpdateCommentDTO){
            User user= userService.getCurrentUser();
            Comment comment = commentRepository.findById(addOrUpdateCommentDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("존재하지 않은 댓글 Id입니다"));
             if (!comment.getUser().equals(user)){
                 throw new PermissionDeniedException("본인이 작성한 댓글에 대해서만 수정이 가능합니다");
             }
                comment.setAddDate(LocalDateTime.now());
                comment.setContent(addOrUpdateCommentDTO.getContent());
                comment.setAlbum(albumRepository.findById(addOrUpdateCommentDTO.getAlbumId()).orElseThrow(()->new ResourceNotFoundException("존재하지 않은 앨범 ID입니다")));
                comment.setUser(user);
        return  commentRepository.save(comment).toAddOrUpdateCommentDTO();
    }
    public AddOrUpdateCommentDTO saveComment(AddOrUpdateCommentDTO addOrUpdateCommentDTO){
                Comment comment = new Comment(
                        null,
                        addOrUpdateCommentDTO.getContent(),
                        LocalDateTime.now(),
                        albumRepository.findById(addOrUpdateCommentDTO.getAlbumId()).orElseThrow(()->new ResourceNotFoundException("존재하지 않은 앨범 ID입니다")),
                        userService.getCurrentUser()
                );
        return  commentRepository.save(comment).toAddOrUpdateCommentDTO();
    }

}
