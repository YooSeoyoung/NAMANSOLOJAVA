package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AddOrUpdateCommentDTO;
import com.dw.NAMANSOLOJAVA.DTO.GreatDTO;
import com.dw.NAMANSOLOJAVA.DTO.GreatToggleResultDTO;
import com.dw.NAMANSOLOJAVA.Service.GreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/great")
public class GreatController {

    @Autowired
    GreatService greatService;

    @PostMapping("/save")
    public ResponseEntity<GreatToggleResultDTO> toggleGreat(@RequestBody GreatDTO greatDTO) {
        return new ResponseEntity<>(
                greatService.toggleGreat(greatDTO),
                HttpStatus.OK);
    }
    @GetMapping("/albumId/{albumId}")
    public ResponseEntity<List<String>> getAllGreats(@PathVariable Long albumId) {
        return new ResponseEntity<>(
                greatService.getAllGreats(albumId),
                HttpStatus.OK);
    }



}
