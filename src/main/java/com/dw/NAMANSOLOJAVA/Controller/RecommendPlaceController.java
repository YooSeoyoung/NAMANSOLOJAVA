package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceDTO;
import com.dw.NAMANSOLOJAVA.Service.RecommendPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendPlaceController {
    @Autowired
    RecommendPlaceService recommendPlaceService;

    @GetMapping("/all")
    public ResponseEntity<List<RecommendPlaceDTO>> getAllRecommendPlaces() {
        return new ResponseEntity<>(recommendPlaceService.getAllRecommendPlaces(), HttpStatus.OK);
    }

    @GetMapping("/single")
    public ResponseEntity<RecommendPlaceDTO> getRecommendPlace() {
        return new ResponseEntity<>(recommendPlaceService.getRecommendPlace(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RecommendPlaceDTO> updateRecommendPlace(@RequestBody RecommendPlaceDTO recommendPlaceDTO) {
        return new ResponseEntity<>(recommendPlaceService.updateRecommendPlace(recommendPlaceDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecommendPlaceById(Long id) {
        return new ResponseEntity<>(recommendPlaceService.deleteRecommendPlace(id),HttpStatus.OK);
    }
}
