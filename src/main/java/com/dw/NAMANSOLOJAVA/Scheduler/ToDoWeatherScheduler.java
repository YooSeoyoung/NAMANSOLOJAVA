package com.dw.NAMANSOLOJAVA.Scheduler;

import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import com.dw.NAMANSOLOJAVA.Service.AlarmService;
import com.dw.NAMANSOLOJAVA.Service.WeatherService;
import com.dw.NAMANSOLOJAVA.model.ToDo;
import com.dw.NAMANSOLOJAVA.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ToDoWeatherScheduler {

    private final ToDoRepository toDoRepository;
    private final WeatherService weatherService;
    private final AlarmService alarmService;

    @Scheduled(cron = "0 0 7 * * *") // 매일 오전 7시 실행
    public void sendWeatherAlarms() {
        LocalDate today = LocalDate.now();
        List<LocalDate> targetDates = List.of(today, today.plusDays(3), today.plusDays(7));

        for (LocalDate date : targetDates) {
            List<ToDo> toDos = toDoRepository.findByTargetDateInRange(date);

            for (ToDo todo : toDos) {
                User user = todo.getUser();
                String city = user.getCity(); // 유저 도시 정보
                String weather = weatherService.getCurrentWeather(city); // 날씨 API 호출

                int daysBetween = (int) ChronoUnit.DAYS.between(today, date);

                String summary;
                boolean isFuture;

                if (daysBetween == 7) {
                    summary = "7일 후 기념일 날씨: " + weather;
                    isFuture = true;
                } else if (daysBetween == 3) {
                    summary = "3일 후 일정 날씨: " + weather;
                    isFuture = false;
                } else if (daysBetween == 0) {
                    summary = weather; // "오늘의 날씨:"는 sendWeatherAlarm 메서드에서 자동 붙음
                    isFuture = false;
                } else {
                    summary = date + " 날씨: " + weather;
                    isFuture = false;
                }

                alarmService.sendWeatherAlarm(user.getUsername(), summary, isFuture);
            }
        }
    }
}