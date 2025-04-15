package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoAllDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoTravelDTO;
import com.dw.NAMANSOLOJAVA.Service.ToDoService;
import com.dw.NAMANSOLOJAVA.Service.UserService;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    @Autowired
    ToDoService toDoService;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<ToDoAllDTO> getAllTodo() {
        return new ResponseEntity<>(toDoService.getAllTodo(), HttpStatus.OK);
    }

    @GetMapping("/anniversary/all")
    public ResponseEntity<List<AnniversaryDTO>> getAllAnniversary() {
        return new ResponseEntity<>(toDoService.getAllAnniversary(), HttpStatus.OK);
    }

    @GetMapping("/travel/all")
    public ResponseEntity<List<ToDoTravelDTO>> getAllTravel() {
        return new ResponseEntity<>(toDoService.getAllTravel(), HttpStatus.OK);
    }

    @PostMapping("/travel/save")
    public ResponseEntity<ToDoTravelDTO> saveTravel(@RequestBody ToDoTravelDTO dto) {
        return new ResponseEntity<>(toDoService.saveTravel(dto), HttpStatus.OK);
    }

    @PostMapping("/anniversary/save")
    public ResponseEntity<AnniversaryDTO> saveAnniversary(@RequestBody AnniversaryDTO anniversaryDTO) {
        return new ResponseEntity<>(toDoService.saveAnniversary(anniversaryDTO), HttpStatus.OK);
    }

    @GetMapping("/anniversary/{id}")
    public ResponseEntity<AnniversaryDTO> getAnniversaryById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getAnniversaryById(id), HttpStatus.OK);
    }

    @GetMapping("/travel/{id}")
    public ResponseEntity<ToDoTravelDTO> getToDoTravelById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getToDoTravelById(id), HttpStatus.OK);
    }

    @PutMapping("/travel/update/{id}")
    public ResponseEntity<ToDoTravelDTO> updateToDoTravelById(@PathVariable Long id, @RequestBody ToDoTravelDTO toDoTravelDTO) {
        toDoTravelDTO.setId(id);
        return new ResponseEntity<>(toDoService.updateToDoTravelById(id, toDoTravelDTO), HttpStatus.OK);
    }

    @PutMapping("/anniversary/update/{id}")
    public ResponseEntity<AnniversaryDTO> updateAnniversaryById(@PathVariable Long id, @RequestBody AnniversaryDTO anniversaryDTO) {
        anniversaryDTO.setId(id);
        return new ResponseEntity<>(toDoService.updateAnniversaryById(id, anniversaryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/anniversary/delete/{id}")
    public ResponseEntity<String> deleteAnniversaryById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.deleteAnniversaryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/travel/delete/{id}")
    public ResponseEntity<String> deleteTravelById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.deleteTravelById(id), HttpStatus.OK);
    }

    @PostMapping("/upload/multiple")
    public ResponseEntity<?> uploadMultiple(
            @RequestParam("title") String title,
            @RequestParam("files") List<MultipartFile> files) {

        User user = userService.getCurrentUser();
        String username = user.getUsername();
        String uploadDir = "./var/upload/" + username;

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        List<MediaDTO> uploadedMedia = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();

            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

            String baseName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + "_" + baseName + ext;

            Path savePath = Paths.get(uploadDir, newFileName);

            try {
                Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

                MediaDTO mediaDTO = new MediaDTO();
                mediaDTO.setMediaUrl("/api/todo/download/" + username + "/" + newFileName);
                mediaDTO.setMediaType(file.getContentType().startsWith("video") ? "VIDEO" : "PICTURE");

                uploadedMedia.add(mediaDTO);

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("업로드 중 오류: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(uploadedMedia);
    }

    @GetMapping("/download/{username}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String username,
            @PathVariable String fileName) {
        try {
            Path basePath = Paths.get("./var/upload").resolve(username).normalize();
            Path filePath = basePath.resolve(fileName).normalize();

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
