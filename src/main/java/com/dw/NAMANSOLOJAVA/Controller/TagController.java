package com.dw.NAMANSOLOJAVA.Controller;


import com.dw.NAMANSOLOJAVA.DTO.AlbumDTO;
import com.dw.NAMANSOLOJAVA.Service.TagService;
import com.dw.NAMANSOLOJAVA.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Tag> getTagId(@PathVariable Long id) {
        return new ResponseEntity<>(
                tagService.getTagId(id),
                HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Tag> getTagName(@PathVariable String name) {
        return new ResponseEntity<>(
                tagService.getTagName(name),
                HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Tag> saveTag(@RequestBody Tag tag) {
        return new ResponseEntity<>(
                tagService.saveTag(tag),
                HttpStatus.OK);
    }
}
