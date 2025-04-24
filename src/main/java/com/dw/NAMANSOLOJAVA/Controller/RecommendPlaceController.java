package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceAdmDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceMultipartDTO;
import com.dw.NAMANSOLOJAVA.Service.RecommendPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/recommend_place")
public class RecommendPlaceController {
    @Autowired
    RecommendPlaceService recommendPlaceService;

    @GetMapping("/all")
    public ResponseEntity<List<RecommendPlaceDTO>> getAllRecommendPlaces() {
        return new ResponseEntity<>(recommendPlaceService.getAllRecommendPlaces(), HttpStatus.OK);
    }

    @GetMapping("/single/{id}")
    public ResponseEntity<RecommendPlaceDTO> getRecommendPlace(@PathVariable Long id) {
        return ResponseEntity.ok(recommendPlaceService.getRecommendPlace(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update")
    public ResponseEntity<RecommendPlaceAdmDTO> updateRecommendPlaceByAdmin(@RequestBody RecommendPlaceAdmDTO recommendPlaceDTO) {
        return new ResponseEntity<>(recommendPlaceService.updateRecommendPlace(recommendPlaceDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecommendPlaceById(@PathVariable Long id) {
        return new ResponseEntity<>(recommendPlaceService.deleteRecommendPlace(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<RecommendPlaceAdmDTO> addRecommendPlaceByAdmin(@RequestBody RecommendPlaceAdmDTO dto) {
        return new ResponseEntity<>(recommendPlaceService.addRecommendPlace(dto), HttpStatus.CREATED);
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<RecommendPlaceDTO>> getRecommendPlacesByRegion(@PathVariable String region) {
        return new ResponseEntity<>(recommendPlaceService.getPlacesByRegion(region), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload/full")
    public ResponseEntity<?> uploadPlaceWithFiles(
            @RequestPart("place") RecommendPlaceAdmDTO dto,
            @RequestPart("files") List<MultipartFile> files
    ) {
        List<MediaDTO> mediaList = recommendPlaceService.saveMediaFiles(files);
        dto.setMediaUrl(mediaList);
        return ResponseEntity.ok(recommendPlaceService.savePlaceWithMedia(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<RecommendPlaceAdmDTO> savePlaceWithMedia(
            @RequestBody RecommendPlaceAdmDTO dto
    ) {
        RecommendPlaceAdmDTO saved = recommendPlaceService.savePlaceWithMedia(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload/image/{placeId}")
    public ResponseEntity<List<MediaDTO>> updatePlaceImage(
            @PathVariable Long placeId,
            @RequestPart("file") MultipartFile file
    ) {
        List<MediaDTO> updatedMedia = recommendPlaceService.updateMediaForPlace(placeId, file);
        return ResponseEntity.ok(updatedMedia);
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadPlaceImage(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();  // ✅ 핵심: 그냥 uploadDir만

            System.out.println("📂 요청된 파일명: [" + fileName + "]");
            System.out.println("📂 최종 경로: " + filePath.toAbsolutePath());

            Resource resource = new UrlResource(filePath.toUri());

            System.out.println("📄 resource.exists(): " + resource.exists());
            System.out.println("📄 resource.isReadable(): " + resource.isReadable());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


}
