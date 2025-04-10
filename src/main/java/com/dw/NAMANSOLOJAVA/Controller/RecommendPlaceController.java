package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceAdmDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceDTO;
import com.dw.NAMANSOLOJAVA.Service.RecommendPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
