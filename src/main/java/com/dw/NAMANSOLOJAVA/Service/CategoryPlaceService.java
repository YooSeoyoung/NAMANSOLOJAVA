package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.CategoryPlaceDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceAdmDTO;
import com.dw.NAMANSOLOJAVA.Repository.CategoryPlaceRepository;
import com.dw.NAMANSOLOJAVA.Repository.CategoryRepository;
import com.dw.NAMANSOLOJAVA.Repository.RecommendPlaceRepository;
import com.dw.NAMANSOLOJAVA.model.Category;
import com.dw.NAMANSOLOJAVA.model.CategoryPlace;
import com.dw.NAMANSOLOJAVA.model.RecommendPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryPlaceService {
    @Autowired
    CategoryPlaceRepository categoryPlaceRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RecommendPlaceRepository recommendPlaceRepository;

    public void addMapping(CategoryPlaceDTO dto) {
        Category category = categoryRepository.findByName(dto.getCategory().getCategoryName())
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));

        RecommendPlace place = recommendPlaceRepository.findById(dto.getRecommendPlaceId())
                .orElseThrow(() -> new RuntimeException("장소 없음"));

        categoryPlaceRepository.save(new CategoryPlace(category, place));
    }

    public void deleteMapping(Long id) {
        categoryPlaceRepository.deleteById(id);
    }

    public List<RecommendPlaceAdmDTO> getPlacesByCategory(String categoryName) {
        List<CategoryPlace> list = categoryPlaceRepository.findAllByCategory_Name(categoryName);

        return list.stream()
                .map(cp -> {
                    RecommendPlaceAdmDTO dto = cp.getRecommendPlace().admDTO();
                    dto.setCategory(cp.getCategory().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<RecommendPlaceAdmDTO> getPlacesByRegionAndCategory(String city, String category) {
        return categoryPlaceRepository
                .findByRecommendPlace_CityAndCategory_Name(city, category)
                .stream()
                .map(cp -> cp.getRecommendPlace().admDTO()) // ✅ CategoryPlace → RecommendPlace → DTO
                .collect(Collectors.toList());
    }
}


