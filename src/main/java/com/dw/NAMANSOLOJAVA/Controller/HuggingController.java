package com.dw.NAMANSOLOJAVA.Controller;


import com.dw.NAMANSOLOJAVA.Service.HuggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hugging")
public class HuggingController {
    @Autowired
    private HuggingService huggingService;

    // 👉 사용자별 장소 기반 데이트 장소 추천
    @GetMapping("/recommend")
    public ResponseEntity<String> getRecommendation() {
        String response = huggingService.getPersonalizedRecommendation();
        return ResponseEntity.ok(response);
    }
}
