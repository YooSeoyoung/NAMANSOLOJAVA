package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceDTO;
import com.dw.NAMANSOLOJAVA.Repository.RecommendPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendPlaceService {
    @Autowired
    RecommendPlaceRepository recommendPlaceRepository;

    public List<RecommendPlaceDTO> getAllRecommendPlaces() {
        return null;
//        recommendPlaceRepository.getAllRecommendPlaces();
    }

    public RecommendPlaceDTO getRecommendPlace() {
        return null;
//        recommendPlaceRepository.getRecommendPlace();
    }

    public RecommendPlaceDTO updateRecommendPlace(RecommendPlaceDTO recommendPlaceDTO) {
        return null;
//        return recommendPlaceRepository.updateRecommendPlace(recommendPlaceDTO);
    }

    public String deleteRecommendPlace(Long id) {
        return null;
//        return recommendPlaceRepository.deleteById(id);
    }
}
