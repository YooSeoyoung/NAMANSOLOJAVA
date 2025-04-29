package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Service.AlbumService;
import com.dw.NAMANSOLOJAVA.Service.UserService;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    AlbumRepository albumRepository;

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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity <String> deleteAlbumByAdmin(@PathVariable Long id) {
        return new ResponseEntity<>(
                albumService.deleteAlbumByAdmin(id),
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
            @RequestParam("files") List<MultipartFile> files) {

        String uploadDir = "./var/upload"; // 공용 폴더
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        List<MediaDTO> uploadedMedia = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extWithDot  = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extWithDot ;
            Path savePath = Paths.get(uploadDir, newFileName);

            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            try {
                Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

                MediaDTO mediaDTO = new MediaDTO();
                mediaDTO.setMediaUrl("/api/album/download/" + newFileName); // ✅ 경로에 username 없음
                if (List.of("mp4", "avi", "mov").contains(ext)) {
                    mediaDTO.setMediaType("VIDEO");
                } else {
                    mediaDTO.setMediaType("PICTURE");
                }

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

    @GetMapping("/daily")
    public List<AlbumDailyDTO> getDailyFeeds(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        LocalDateTime start = from.atStartOfDay(); // 2025-04-01 00:00
        LocalDateTime end = to.atTime(23, 59, 59); // 2025-04-10 23:59:59

        return albumRepository.countDailyFeeds(start, end);
    }

    @GetMapping("/rank")
    public List<AlbumRankDTO> getMonthlyRank(@RequestParam("month") String month) {
        return albumRepository.countFeedsByUserPerMonth(month);
    }
}
