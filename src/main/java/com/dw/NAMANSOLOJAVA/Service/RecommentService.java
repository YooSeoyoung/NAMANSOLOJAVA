package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateReCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.CommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.ReCommentDTO;
import com.dw.NAMANSOLOJAVA.Exception.PermissionDeniedException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.CommentRepository;
import com.dw.NAMANSOLOJAVA.Repository.RecommentRepository;
import com.dw.NAMANSOLOJAVA.model.Comment;
import com.dw.NAMANSOLOJAVA.model.ReComment;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecommentService {

    @Autowired
    RecommentRepository recommentRepository;

    @Autowired
    UserService userService;
    @Autowired
    CommentRepository commentRepository;

    public ReCommentDTO getReCommentById(Long id){
        return  recommentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("존재하지 않은  ID입니다")).toRecommentDTO();
    }

    public List<ReCommentDTO> getReCommentByCommentId(Long commentId){
        return  recommentRepository.findByCommentId(commentId).stream().map(ReComment::toRecommentDTO).toList();
    }

    public String deleteReCommentById(Long id){
        User user =userService.getCurrentUser();
        ReComment reComment = recommentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("존재하지 않은 ID입니다"));
        if (!reComment.getUser().equals(user)){
            throw new PermissionDeniedException("본인의 대댓글에 대해서만 삭제가 가능합니다");
        }
        recommentRepository.deleteById(id);
        return  "대댓글이 정상 삭제되었습니다";
    }

    public AddOrUpdateReCommentDTO updateReComment(AddOrUpdateReCommentDTO addOrUpdateReCommentDTO){
        User user= userService.getCurrentUser();
        ReComment reComment = recommentRepository.findById(addOrUpdateReCommentDTO.getId()).orElseThrow(()->new ResourceNotFoundException("존재하지 않은 ID입니다"));
        if (!reComment.getUser().equals(user)){
            throw new PermissionDeniedException("본인의 대댓글에 대해서만 수정이 가능합니다");
        }
        reComment.setContent(addOrUpdateReCommentDTO.getContent());
        reComment.setAddDate(LocalDateTime.now());
        reComment.setComment(commentRepository.findById(addOrUpdateReCommentDTO.getCommentId()).orElseThrow(()->new ResourceNotFoundException("존재하지 않은 댓글 ID입니다")));
        reComment.setUser(user);
        return  recommentRepository.save(reComment).toAddOrUpdateReCommentDTO();
    }
    public AddOrUpdateReCommentDTO saveReComment(AddOrUpdateReCommentDTO addOrUpdateReCommentDTO){
            ReComment reComment=new ReComment(
                    null,
                    addOrUpdateReCommentDTO.getContent(),
                    LocalDateTime.now(),
                    commentRepository.findById(addOrUpdateReCommentDTO.getCommentId()).orElseThrow(()->new ResourceNotFoundException("존재하지 않은 댓글 ID입니다")),
                    userService.getCurrentUser()
                    );
        return  recommentRepository.save(reComment).toAddOrUpdateReCommentDTO();
    }
}
