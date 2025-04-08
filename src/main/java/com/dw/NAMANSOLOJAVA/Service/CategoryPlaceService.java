package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.CategoryPlaceDTO;
import com.dw.NAMANSOLOJAVA.Repository.CategoryPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class CategoryPlaceService {
    @Autowired
    CategoryPlaceRepository categoryPlaceRepository;

    public CategoryPlaceDTO saveCategoryPlace(CategoryPlaceDTO categoryPlaceDTO) {
        return null;
//        return CategoryPlaceRepository.saveCategoryPlace(categoryPlaceDTO);
    }

    @GetMapping("/all")
    public CategoryPlaceDTO getAllCategoryPlaces() {
        return null;
//        return categoryPlaceRepository.getAllCategoryPlaces();
    }

    @GetMapping("/single/{id}")
    public CategoryPlaceDTO getSingleCategoryPlace(Long id) {
        return null;
//        return categoryPlaceRepository.getSingleCategoryPlace(id);
    }

    @PutMapping("/update")
    public CategoryPlaceDTO updateCategoryPlace(CategoryPlaceDTO categoryPlaceDTO) {
        return null;
//        return categoryPlaceRepository.updateCategoryPlace(categoryPlaceDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategoryPlace(@PathVariable Long id) {
        return null;
//        return categoryPlaceRepository.deleteCategoryPlace(id);
    }
}
