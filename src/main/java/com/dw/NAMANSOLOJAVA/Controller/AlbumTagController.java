package com.dw.NAMANSOLOJAVA.Controller;


import com.dw.NAMANSOLOJAVA.DTO.AlbumDTO;
import com.dw.NAMANSOLOJAVA.DTO.AlbumTagDTO;
import com.dw.NAMANSOLOJAVA.DTO.ReCommentDTO;
import com.dw.NAMANSOLOJAVA.Service.AlbumTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/album-tag")
public class AlbumTagController {

    @Autowired
    AlbumTagService albumTagService;

    @PostMapping("/save")
    public ResponseEntity<List<AlbumTagDTO>>SaveTagToAlbum(@RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(
                albumTagService.SaveTagToAlbum(albumDTO),
                HttpStatus.OK);
    }
    @GetMapping("/album-id/{albumId}")
    public ResponseEntity<List<AlbumTagDTO>> getAllAlbumTagByAlbumId(@PathVariable Long albumId) {
        return new ResponseEntity<>(
                albumTagService.getAllAlbumTagByAlbumId(albumId),
                HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteAlbumTagById(@PathVariable Long id) {
        return new ResponseEntity<>(
                albumTagService.deleteAlbumTagById(id),
                HttpStatus.OK);
    }
}
