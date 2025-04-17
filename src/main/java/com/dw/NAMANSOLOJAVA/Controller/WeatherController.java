package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.Scheduler.ToDoWeatherScheduler;
import com.dw.NAMANSOLOJAVA.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final ToDoWeatherScheduler toDoWeatherScheduler;

    // 기존 날씨 조회용
    // 프론트에서: /api/weather?city=Seoul
    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        String result = weatherService.getCurrentWeather(city);
        return ResponseEntity.ok(result);
    }

    // 수동 날씨 알람 전송용
    @PostMapping("/weather/send-now")
    public ResponseEntity<String> triggerManualWeatherAlarm() {
        toDoWeatherScheduler.sendWeatherAlarms(); // 수동 실행
        return ResponseEntity.ok("날씨 알람 수동 실행 완료");
    }
}
