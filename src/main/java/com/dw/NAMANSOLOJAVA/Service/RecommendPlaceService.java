package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Config.SecurityConfig;
import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceAdmDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceMultipartDTO;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.*;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
import com.dw.NAMANSOLOJAVA.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.stream.Collectors;

@Service
public class RecommendPlaceService {
    @Autowired
    RecommendPlaceRepository recommendPlaceRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    CategoryPlaceRepository categoryPlaceRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AlarmService alarmService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public List<RecommendPlaceDTO> getAllRecommendPlaces() {
        return recommendPlaceRepository.findAllWithMedia().stream()
                .map(RecommendPlace::placeDTO)
                .collect(Collectors.toList());
    }

    public RecommendPlaceDTO getRecommendPlace(Long id) {
        return recommendPlaceRepository.findById(id)
                .map(RecommendPlace::placeDTO)
                .orElseThrow(() -> new ResourceNotFoundException("추천 장소가 없습니다."));
    }

    public RecommendPlaceAdmDTO updateRecommendPlace(RecommendPlaceAdmDTO recommendPlaceDTO) {
        RecommendPlace place = recommendPlaceRepository.findById(recommendPlaceDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("해당 ID의 장소가 존재하지 않습니다."));

        if (recommendPlaceDTO.getName() == null || recommendPlaceDTO.getName().isBlank()) {
            throw new InvalidRequestException("장소 이름은 필수입니다.");
        }

        List<Media> mediaList = recommendPlaceDTO.getMediaUrl().stream()
                .map(mediaDTO -> mediaRepository.findById(mediaDTO.getId())
                        .orElseGet(() -> new Media(
                                null,
                                mediaDTO.getMediaUrl(),
                                MediaType.valueOf(mediaDTO.getMediaType())
                        )))
                .collect(Collectors.toList());

        mediaRepository.saveAll(mediaList);

        place.setName(recommendPlaceDTO.getName());
        place.setAddress(recommendPlaceDTO.getAddress());
        place.setCity(recommendPlaceDTO.getCity());
        place.setLatitude(recommendPlaceDTO.getLatitude());
        place.setLongitude(recommendPlaceDTO.getLongitude());
        place.setDescription(recommendPlaceDTO.getDescription());
        place.setDetail(recommendPlaceDTO.getDetail());
        place.setMedia(mediaList);

        return recommendPlaceRepository.save(place).admDTO();
    }

    @Transactional
    public String deleteRecommendPlace(Long id) {
        RecommendPlace place = recommendPlaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("삭제할 장소가 존재하지 않습니다."));

        List<Media> mediaList = place.getMedia();
        for (Media media : mediaList) {
            String relativePath = media.getMediaUrl().replace("/api/recommend_place/download/", "");
            Path filePath = Paths.get("./var/uploads").resolve(relativePath);

            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        recommendPlaceRepository.delete(place);
        return "삭제 완료: id = " + id;
    }


    public RecommendPlaceAdmDTO addRecommendPlace(RecommendPlaceAdmDTO dto) {
        User currentUser = userService.getCurrentUser();
        if (!currentUser.getAuthority().getAuthorityName().equals("ROLE_ADMIN")) {
            throw new AccessDeniedException("관리자만 추천 장소를 등록할 수 있습니다.");
        }
        List<Media> mediaList = dto.getMediaUrl().stream()
                .map(mediaDTO -> mediaRepository.findById(mediaDTO.getId())
                        .orElseGet(() -> new Media(
                                null,
                                mediaDTO.getMediaUrl(),
                                MediaType.valueOf(mediaDTO.getMediaType())
                        )))
                .collect(Collectors.toList());
        mediaRepository.saveAll(mediaList);

        RecommendPlace place = new RecommendPlace();
        place.setName(dto.getName());
        place.setMedia(mediaList);
        place.setAddress(dto.getAddress());
        place.setCity(dto.getCity());
        place.setLatitude(dto.getLatitude());
        place.setLongitude(dto.getLongitude());
        place.setDescription(dto.getDescription());
        place.setDetail(dto.getDetail());

        recommendPlaceRepository.save(place);

        RecommendPlaceAdmDTO result = place.admDTO();

        if (dto.getCategory() != null && !dto.getCategory().isBlank()) {
            Category category = categoryRepository.findByName(dto.getCategory())
                    .orElseGet(() -> categoryRepository.save(new Category(dto.getCategory())));

            categoryPlaceRepository.save(new CategoryPlace(category, place));
            result.setCategory(category.getName());
        }
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            alarmService.sendPlaceRecommendAlarm(user.getUsername(), place.getName());
        }

        return result;
    }

    public List<RecommendPlaceDTO> getPlacesByRegion(String region) {
        return recommendPlaceRepository.findByCity(region).stream()
                .map(RecommendPlace::placeDTO)
                .collect(Collectors.toList());
    }

    public List<MediaDTO> saveMediaFiles(List<MultipartFile> files) {
        String uploadDir = "./var/uploads";

        File folder = new File(uploadDir);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            System.out.println("📂 폴더 생성됨? " + created);
        }

        return files.stream().map(file -> {
            String originalFilename = file.getOriginalFilename();
            String uuidFilename = UUID.randomUUID() + "_" + originalFilename;

            Path savePath = Paths.get(uploadDir, uuidFilename);

            try {
                Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }

            Media media = new Media();
            media.setMediaUrl("/api/recommend_place/download/" + uuidFilename);  // ✔️ URL 형식도 명확하게
            media.setMediaType(file.getContentType().startsWith("video") ? MediaType.VIDEO : MediaType.PICTURE);
            mediaRepository.save(media);

            MediaDTO dto = new MediaDTO();
            dto.setId(media.getId());
            dto.setMediaUrl(media.getMediaUrl());
            dto.setMediaType(media.getMediaType().name());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<MediaDTO> updateMediaForPlace(Long placeId, MultipartFile file) {
        RecommendPlace place = recommendPlaceRepository.findById(placeId)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found"));

        List<Media> oldMedia = place.getMedia();
        for (Media media : oldMedia) {
            mediaRepository.delete(media);
        }

        List<MediaDTO> newMediaList = saveMediaFiles(List.of(file));


        List<Media> mediaList = new ArrayList<>();
        for (MediaDTO dto : newMediaList) {
            Media media = new Media();
            media.setId(dto.getId());
            media.setMediaUrl(dto.getMediaUrl());
            mediaList.add(media);
        }

        place.setMedia(mediaList);
        recommendPlaceRepository.save(place);

        return newMediaList;
    }



    public RecommendPlaceAdmDTO savePlaceWithMedia(RecommendPlaceAdmDTO dto) {
        RecommendPlace place;

        if (dto.getId() != null) {
            place = recommendPlaceRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("ID에 해당하는 추천 장소가 없습니다."));
        } else {
            place = new RecommendPlace();
        }

        List<Media> mediaList = dto.getMediaUrl().stream()
                .map(m -> mediaRepository.findById(m.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("파일 없음: id=" + m.getId())))
                .collect(Collectors.toList());

        place.setName(dto.getName());
        place.setAddress(dto.getAddress());
        place.setCity(dto.getCity());
        place.setLatitude(dto.getLatitude());
        place.setLongitude(dto.getLongitude());
        place.setDescription(dto.getDescription());
        place.setDetail(dto.getDetail());
        place.setMedia(mediaList);

        recommendPlaceRepository.save(place);

        RecommendPlaceAdmDTO result = place.admDTO();

        if (dto.getCategory() != null && !dto.getCategory().isBlank()) {
            Category category = categoryRepository.findByName(dto.getCategory())
                    .orElseGet(() -> categoryRepository.save(new Category(dto.getCategory())));

            categoryPlaceRepository.save(new CategoryPlace(category, place));
            result.setCategory(category.getName());
        }

        return result;
    }



}