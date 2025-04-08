package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateReCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.CommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.ReCommentDTO;
import com.dw.NAMANSOLOJAVA.Repository.RecommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommentService {

    @Autowired
    RecommentRepository recommentRepository;


    public ReCommentDTO getReCommentById(Long id){
        return  null;
    }

    public List<ReCommentDTO> getReCommentByCommentId(Long commentId){
        return  null;
    }

    public String deleteReCommentById(Long id){
        return  null;
    }

    public AddOrUpdateReCommentDTO updateReComment(AddOrUpdateReCommentDTO addOrUpdateReCommentDTO){
        return  null;
    }
    public AddOrUpdateReCommentDTO saveReComment(AddOrUpdateReCommentDTO addOrUpdateReCommentDTO){
        return  null;
    }
}
