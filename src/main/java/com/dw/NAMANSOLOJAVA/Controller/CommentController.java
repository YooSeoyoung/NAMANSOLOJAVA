package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.CommentDTO;
import com.dw.NAMANSOLOJAVA.Service.CommentService;
import com.dw.NAMANSOLOJAVA.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/id/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(
                commentService.getCommentById(id),
                HttpStatus.OK);
    }
    @GetMapping("/album-id/{albumId}")
    public ResponseEntity<List<CommentDTO>> getCommentByAlbumId(@PathVariable Long albumId) {
        return new ResponseEntity<>(
                commentService.getCommentByAlbumId(albumId),
                HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<CommentDTO>> getCommentByUsername() {
        return new ResponseEntity<>(
                commentService.getCommentByUsername(),
                HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(
                commentService.deleteCommentById(id),
                HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<AddOrUpdateCommentDTO> updateComment(@RequestBody AddOrUpdateCommentDTO addOrUpdateCommentDTO) {
        return new ResponseEntity<>(
                commentService.updateComment(addOrUpdateCommentDTO),
                HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<AddOrUpdateCommentDTO> saveComment(@RequestBody AddOrUpdateCommentDTO addOrUpdateCommentDTO) {
        return new ResponseEntity<>(
                commentService.saveComment(addOrUpdateCommentDTO),
                HttpStatus.OK);
    }




}
