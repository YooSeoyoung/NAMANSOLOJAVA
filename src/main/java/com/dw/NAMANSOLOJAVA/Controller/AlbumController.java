package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AddAlbumDTO;
import com.dw.NAMANSOLOJAVA.DTO.BookmarkDTO;
import com.dw.NAMANSOLOJAVA.DTO.UpdateAlbumDTO;
import com.dw.NAMANSOLOJAVA.DTO.AlbumDTO;
import com.dw.NAMANSOLOJAVA.Service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/all")
    public ResponseEntity<List<AlbumDTO>> getAllAlbum() {
        return new ResponseEntity<>(
                albumService.getAllAlbum(),
                HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity <AlbumDTO> getAlbumById(@PathVariable Long id) {
        return new ResponseEntity<>(
                albumService.getAlbumById(id),
                HttpStatus.OK);
    }
    @GetMapping("/ids")
    public ResponseEntity <List<BookmarkDTO>> getAlbumByIds(@RequestParam List<Long> id) {
        return new ResponseEntity<>(
                albumService.getAlbumByIds(id),
                HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity <UpdateAlbumDTO> UpdateAlbum( @RequestBody UpdateAlbumDTO updateAlbumDTO) {
        return new ResponseEntity<>(
                albumService.updateAlbum(updateAlbumDTO),
                HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity <AddAlbumDTO> SaveAlbum(@RequestBody AddAlbumDTO addAlbumDTO) {
        return new ResponseEntity<>(
                albumService.saveAlbum(addAlbumDTO),
                HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity <String> deleteAlbumById(@PathVariable Long id) {
        return new ResponseEntity<>(
                albumService.deleteAlbumById(id),
                HttpStatus.OK);
    }

    @GetMapping("/username-visibility/{username}")
    public ResponseEntity <List<AlbumDTO>> getAlbumByUsernameAndVisibility(@PathVariable String username) {
        return new ResponseEntity<>(
                albumService.getAlbumByUsernameAndVisibility(username),
                HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity <List<AlbumDTO>> getAlbumByUsername(@PathVariable String username) {
        return new ResponseEntity<>(
                albumService.getAlbumByUsername(username),
                HttpStatus.OK); // 나의 앨범 보기 (공개 비공개 여부 상관 없음)
    }



}
