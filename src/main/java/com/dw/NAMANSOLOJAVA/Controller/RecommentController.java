package com.dw.NAMANSOLOJAVA.Controller;


import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Service.RecommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recomment")
public class RecommentController {

    @Autowired
    RecommentService recommentService;

    @GetMapping("/id/{id}")
    public ResponseEntity<ReCommentDTO> getReCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(
                recommentService.getReCommentById(id),
                HttpStatus.OK);
    }
    @GetMapping("/comment-id/{commentId}")
    public ResponseEntity<List<ReCommentWithAlbumDTO>> getReCommentByCommentId(@PathVariable Long commentId) {
        return new ResponseEntity<>(
                recommentService.getReCommentByCommentId(commentId),
                HttpStatus.OK);
    }
    @GetMapping("/username")
    public ResponseEntity<List<ReCommentWithAlbumDTO>> getReCommentByUsername() {
        return new ResponseEntity<>(
                recommentService.getReCommentByUsername(),
                HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteReCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(
                recommentService.deleteReCommentById(id),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <String> deleteReCommentByAdmin(@PathVariable Long id) {
        return new ResponseEntity<>(
                recommentService.deleteReCommentByAdmin(id),
                HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<AddOrUpdateReCommentDTO> updateReComment(@RequestBody AddOrUpdateReCommentDTO addOrUpdateReCommentDTO) {
        return new ResponseEntity<>(
                recommentService.updateReComment(addOrUpdateReCommentDTO),
                HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<AddOrUpdateReCommentDTO> saveComment(@RequestBody AddOrUpdateReCommentDTO addOrUpdateReCommentDTO) {
        return new ResponseEntity<>(
                recommentService.saveReComment(addOrUpdateReCommentDTO),
                HttpStatus.OK);
    }
}
