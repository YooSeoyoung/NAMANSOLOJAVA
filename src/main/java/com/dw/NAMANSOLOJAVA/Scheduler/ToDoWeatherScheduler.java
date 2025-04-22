package com.dw.NAMANSOLOJAVA.Scheduler;

import com.dw.NAMANSOLOJAVA.DTO.WeatherDTO;
import com.dw.NAMANSOLOJAVA.Repository.ToDoRepository;
import com.dw.NAMANSOLOJAVA.Repository.AlarmRepository;
import com.dw.NAMANSOLOJAVA.Service.WeatherService;
import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import com.dw.NAMANSOLOJAVA.model.Alarm;
import com.dw.NAMANSOLOJAVA.model.ToDo;
import com.dw.NAMANSOLOJAVA.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ToDoWeatherScheduler {

    private final ToDoRepository toDoRepository;
    private final WeatherService weatherService;
    private final AlarmRepository alarmRepository;

    @Scheduled(cron = "0 15 9 * * *") // 매일 오전 9시 15분 실행
    public void scheduledSendWeatherAlarms() {
        System.out.println("🚀 [스케줄러 시작] 일정 + 날씨 알림 확인");

        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.plusDays(3); // 정확히 3일 후

        List<ToDo> toDos = toDoRepository.findByStartDate(targetDate); // 3일 후 일정만 가져오기

        for (ToDo todo : toDos) {
            User user = todo.getUser();
            String username = user.getUsername();
            String city = user.getCity();
            LocalDate scheduleDate = todo.getStartDate();

            if (city == null || city.isBlank()) {
                System.out.println("❌ 사용자 도시 정보 없음, 건너뜀: " + username);
                continue;
            }

            int offset = (int) ChronoUnit.DAYS.between(today, scheduleDate);
            if (offset != 3) continue;

            WeatherDTO weather = weatherService.getWeatherForecast(city, offset);
            if (weather == null) {
                System.out.println("❌ 날씨 정보 조회 실패 → " + city);
                continue;
            }

            // ✅ 최종 메시지: 일정 + 날씨 포함
            String combinedMessage = "'" + todo.getTitle() + "' 일정이 3일 후입니다! 🌦 예상 날씨: "
                    + weather.getDescription() + ", " + weather.getTemp() + "도";

            System.out.println("📨 최종 메시지 생성: " + combinedMessage);

            Optional<Alarm> existing = alarmRepository.findFirstByUser_UsernameAndAlarmTypeAndMessageOrderByAddDateDesc(
                    username, AlarmType.TODO, combinedMessage);
            if (existing.isPresent()) {
                System.out.println("⚠️ 중복 알림 존재, 생략: " + username);
                continue;
            }

            Alarm todoAlarm = new Alarm();
            todoAlarm.setUser(user);
            todoAlarm.setAlarmType(AlarmType.TODO);
            todoAlarm.setMessage(combinedMessage); // ✅ 여기 주의: 날씨 포함
            todoAlarm.setIcon(weather.getIcon());
            todoAlarm.setAddDate(LocalDateTime.now());
            todoAlarm.setRead(false);

            alarmRepository.save(todoAlarm);
            System.out.println("📩 일정 + 날씨 알림 전송 완료 → " + username);
        }
    }
}
