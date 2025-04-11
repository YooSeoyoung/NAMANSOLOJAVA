package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceAdmDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceDTO;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.CategoryPlaceRepository;
import com.dw.NAMANSOLOJAVA.Repository.CategoryRepository;
import com.dw.NAMANSOLOJAVA.Repository.MediaRepository;
import com.dw.NAMANSOLOJAVA.Repository.RecommendPlaceRepository;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
import com.dw.NAMANSOLOJAVA.model.Category;
import com.dw.NAMANSOLOJAVA.model.CategoryPlace;
import com.dw.NAMANSOLOJAVA.model.Media;
import com.dw.NAMANSOLOJAVA.model.RecommendPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<RecommendPlaceDTO> getAllRecommendPlaces() {
        return recommendPlaceRepository.findAll().stream()
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

    public String deleteRecommendPlace(Long id) {
        RecommendPlace place = recommendPlaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("삭제할 장소가 존재하지 않습니다."));

        recommendPlaceRepository.delete(place);
        return "삭제 완료: id = " + id;
    }

    public RecommendPlaceAdmDTO addRecommendPlace(RecommendPlaceAdmDTO dto) {
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

        return result;
    }

    public List<RecommendPlaceDTO> getPlacesByRegion(String region) {
        return recommendPlaceRepository.findByCity(region).stream()
                .map(RecommendPlace::placeDTO)
                .collect(Collectors.toList());
    }
}