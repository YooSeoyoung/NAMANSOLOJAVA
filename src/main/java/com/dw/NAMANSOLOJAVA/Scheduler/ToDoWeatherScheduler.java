package com.dw.NAMANSOLOJAVA.Scheduler;

import com.dw.NAMANSOLOJAVA.DTO.WeatherDTO;
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

    @Scheduled(cron = "0 15 9 * * *") // 매일 오후 8시 50분
    public void scheduledSendWeatherAlarms() {
        LocalDate today = LocalDate.now();
        List<LocalDate> targetDates = List.of(today.plusDays(3), today.plusDays(7)); // 3일, 7일 후

        for (LocalDate date : targetDates) {
            List<ToDo> toDos = toDoRepository.findByTargetDateInRange(date);
            for (ToDo todo : toDos) {
                User user = todo.getUser();
                String city = user.getCity();

                WeatherDTO weather = weatherService.getWeatherDetail(city); // 🌤️ 아이콘 포함된 DTO 사용

                if (weather != null) {
                    int daysBetween = (int) ChronoUnit.DAYS.between(today, date);
                    alarmService.sendWeatherAlarm(user.getUsername(), weather, daysBetween == 7); // 🔔 알림 전송
                }
            }
        }
    }
}