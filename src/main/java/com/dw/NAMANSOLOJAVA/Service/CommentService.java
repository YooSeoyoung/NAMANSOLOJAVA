package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.CommentDTO;
import com.dw.NAMANSOLOJAVA.Repository.CommentRepository;
import com.dw.NAMANSOLOJAVA.model.Comment;
import com.dw.NAMANSOLOJAVA.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public CommentDTO getCommentById(Long id){
        return  null;
    }

    public List<CommentDTO> getCommentByAlbumId(Long albumId){
        return  null;
    }

    public String deleteCommentById(Long id){
        return  null;
    }

    public AddOrUpdateCommentDTO updateComment( AddOrUpdateCommentDTO addOrUpdateCommentDTO){
        return  null;
    }
    public AddOrUpdateCommentDTO saveComment(AddOrUpdateCommentDTO addOrUpdateCommentDTO){
        return  null;
    }

}
