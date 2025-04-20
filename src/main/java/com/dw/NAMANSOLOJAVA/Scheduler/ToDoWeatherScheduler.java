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

    @Scheduled(cron = "0 0 8 * * *") // 매일 오전 8시 자동 실행
    public void scheduledSendWeatherAlarms() {
        LocalDate today = LocalDate.now();
        List<LocalDate> targetDates = List.of(today.plusDays(3), today.plusDays(7)); // 3일, 7일 전만

        for (LocalDate date : targetDates) {
            List<ToDo> toDos = toDoRepository.findByTargetDateInRange(date);
            for (ToDo todo : toDos) {
                User user = todo.getUser();
                String city = user.getCity();
                String weather = weatherService.getCurrentWeather(city);

                int daysBetween = (int) ChronoUnit.DAYS.between(today, date);
                String summary = switch (daysBetween) {
                    case 7 -> "7일 후 기념일 날씨: " + weather;
                    case 3 -> "3일 후 일정 날씨: " + weather;
                    default -> null;
                };

                if (summary != null) {
                    alarmService.sendWeatherAlarm(user.getUsername(), summary, daysBetween == 7);
                }
            }
        }
    }
}