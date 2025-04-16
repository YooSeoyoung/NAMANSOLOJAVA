package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
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
    @Autowired
    AlarmService alarmService;

    public ReCommentDTO getReCommentById(Long id){
        return  recommentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("존재하지 않은  ID입니다")).toRecommentDTO();
    }

    public List<ReCommentWithAlbumDTO> getReCommentByCommentId(Long commentId){
      Long albumId =  commentRepository.findById(commentId).map(comment -> comment.getAlbum().getId())  .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        return  recommentRepository.findByCommentId(commentId).stream()
                .map(reComment -> {
                return   new ReCommentWithAlbumDTO(
                            reComment.getId(),
                            reComment.getContent(),
                            reComment.getAddDate(),
                            reComment.getComment().getId(),
                            reComment.getUser().getUsername()
                            , albumId);
                }).toList();
    }
    public List<ReCommentWithAlbumDTO> getReCommentByUsername(){
        User user =userService.getCurrentUser();
        return recommentRepository.findByUser_Username(user.getUsername()).stream()
                .map(reComment -> {
                    Long albumId = reComment.getComment().getAlbum().getId(); // 답글 → 댓글 → 앨범 ID
                    return new ReCommentWithAlbumDTO(
                            reComment.getId(),
                            reComment.getContent(),
                            reComment.getAddDate(),
                            reComment.getComment().getId(),
                            reComment.getUser().getUsername(),
                            albumId
                    );
                })
                .toList();
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
        User user = userService.getCurrentUser();

        Comment parentComment = commentRepository.findById(addOrUpdateReCommentDTO.getCommentId()).orElseThrow(()->new ResourceNotFoundException("존재하지 않은 댓글 ID입니다"));

                ReComment reComment=new ReComment(
                    null,
                    addOrUpdateReCommentDTO.getContent(),
                    LocalDateTime.now(),
                    parentComment,
                    user
                    );
        AddOrUpdateReCommentDTO result = recommentRepository.save(reComment).toAddOrUpdateReCommentDTO();
        alarmService.sendReCommentAlarm(parentComment.getUser().getUsername(),user.getUsername());

        return result;
    }
}
