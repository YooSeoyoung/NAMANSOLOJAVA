package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.Service.GPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatBotController {

    @Autowired
    private GPTService gptService;

    @GetMapping("/recommend")
    public ResponseEntity<String> recommend() {
        String result = gptService.getPersonalizedRecommendation();
        return ResponseEntity.ok(result);
    }
}
