package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    // 프론트에서: /api/weather?city=Seoul
    @GetMapping
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        String result = weatherService.getCurrentWeather(city);
        return ResponseEntity.ok(result);
    }
}
