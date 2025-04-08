package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.CategoryPlaceDTO;
import com.dw.NAMANSOLOJAVA.Service.CategoryPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/category_place")
public class CategoryPlaceController {
    @Autowired
    CategoryPlaceService categoryPlaceService;

    @PostMapping("/save")
    public ResponseEntity<CategoryPlaceDTO> saveCategoryPlace(CategoryPlaceDTO categoryPlaceDTO) {
        return new ResponseEntity<>(categoryPlaceService.saveCategoryPlace(categoryPlaceDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<CategoryPlaceDTO> getAllCategoryPlaces() {
        return new ResponseEntity<>(categoryPlaceService.getAllCategoryPlaces(), HttpStatus.OK);
    }

    @GetMapping("/single/{id}")
    public ResponseEntity<CategoryPlaceDTO> getSingleCategoryPlace(@PathVariable Long id) {
        return new ResponseEntity<>(categoryPlaceService.getSingleCategoryPlace(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryPlaceDTO> updateCategoryPlace(CategoryPlaceDTO categoryPlaceDTO) {
        return new ResponseEntity<>(categoryPlaceService.updateCategoryPlace(categoryPlaceDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryPlace(@PathVariable Long id) {
        return new ResponseEntity<>(categoryPlaceService.deleteCategoryPlace(id), HttpStatus.ACCEPTED);
    }
}
