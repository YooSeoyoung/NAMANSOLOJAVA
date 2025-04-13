package com.dw.NAMANSOLOJAVA.Controller;


import com.dw.NAMANSOLOJAVA.Service.HuggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hugging")
public class HuggingController {
    @Autowired
    private HuggingService huggingService;

    @GetMapping("/recommend")
    public ResponseEntity<String> recommend(@RequestParam String username, @RequestParam int count) {
        String recommendation = huggingService.getPersonalizedRecommendation(count);
        return ResponseEntity.ok(recommendation);
    }
}