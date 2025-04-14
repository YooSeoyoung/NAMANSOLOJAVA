package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Service.AlbumService;
import com.dw.NAMANSOLOJAVA.Service.UserService;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;
    @Autowired
    UserService userService;

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
    @PostMapping("/upload/multiple")
    public ResponseEntity<?> uploadMultiple(
            @RequestParam("title") String title,
            @RequestParam("files") List<MultipartFile> files) {

        String uploadDir = "./var/upload"; // 공용 폴더
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        List<MediaDTO> uploadedMedia = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + ext;
            Path savePath = Paths.get(uploadDir, newFileName);

            try {
                Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

                MediaDTO mediaDTO = new MediaDTO();
                mediaDTO.setMediaUrl("/api/album/download/" + newFileName); // ✅ 경로에 username 없음
                mediaDTO.setMediaType(file.getContentType().startsWith("video") ? "VIDEO" : "PICTURE");

                uploadedMedia.add(mediaDTO);

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("업로드 중 오류: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(uploadedMedia); // ✅ 프론트에 MediaDTO 리스트 반환
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("./var/upload").resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
