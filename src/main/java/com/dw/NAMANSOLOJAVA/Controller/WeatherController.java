package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.WeatherDTO;
import com.dw.NAMANSOLOJAVA.Scheduler.ToDoWeatherScheduler;
import com.dw.NAMANSOLOJAVA.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final ToDoWeatherScheduler toDoWeatherScheduler;

    // 기존 날씨 조회용
    // 프론트에서: /api/weather?city=Seoul
    @GetMapping("/{city}")
    public ResponseEntity<String> getWeather(@PathVariable String city) {
        String result = weatherService.getCurrentWeather(city);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/forecast")
    public ResponseEntity<WeatherDTO> getWeatherForecast(
            @RequestParam String city,
            @RequestParam int days
    ) {
        WeatherDTO forecast = weatherService.getWeatherForecast(city, days);
        if (forecast != null) {
            return ResponseEntity.ok(forecast);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private final ToDoWeatherScheduler scheduler;

    @PostMapping("/send-now")
    public ResponseEntity<String> triggerManualWeatherAlarm() {
        scheduler.scheduledSendWeatherAlarms(); // ✅ 강제 실행
        return ResponseEntity.ok("수동으로 날씨 알림 실행 완료");
    }

//    // 수동 날씨 알람 전송용
//    @PostMapping("/send-now")
//    public ResponseEntity<String> triggerManualWeatherAlarm() {
//        toDoWeatherScheduler.sendWeatherAlarms(); // 수동 실행
//        return ResponseEntity.ok("날씨 알람 수동 실행 완료");
//    }
}
