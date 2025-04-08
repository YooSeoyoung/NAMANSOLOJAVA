package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.PictureAndVideoDTO;
import com.dw.NAMANSOLOJAVA.Service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @PostMapping("/save/single")
    public ResponseEntity<PictureAndVideoDTO> saveSingleMedia(@RequestBody MediaDTO mediaDTO) {
        return new ResponseEntity<>(mediaService.saveSingleMedia(mediaDTO), HttpStatus.CREATED);
    }

    @PostMapping("/save/multiple")
    public ResponseEntity<List<PictureAndVideoDTO>> saveMultipleMedia(@RequestBody List<MediaDTO> mediaDTOs) {
        return new ResponseEntity<>(mediaService.saveMultipleMedia(mediaDTOs), HttpStatus.CREATED);
    }

    @GetMapping("/multiple")
    public ResponseEntity<List<PictureAndVideoDTO>> getMultipleMedia() {
        return new ResponseEntity<>(mediaService.getMultipleMedia(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PictureAndVideoDTO> deleteSingleMediaById(@PathVariable Long id) {
        return new ResponseEntity<>(mediaService.deleteSingleMediaById(id), HttpStatus.ACCEPTED);
    }
}
