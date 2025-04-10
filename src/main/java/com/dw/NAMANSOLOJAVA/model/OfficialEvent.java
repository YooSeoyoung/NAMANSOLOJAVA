package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.OfficialEventDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "official_event")
public class OfficialEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate; // 기념일 날짜

    @Column(name = "event_title", nullable = false)
    private String eventTitle; // 발렌타인, 크리스마스 등

    @Column(name = "offset_days") // 동적이면 여기에 값 들어감
    private Long offsetDays = 0L; // 기본 0이면 고정 이벤트로 판단

    @Column(name = "editable", nullable = false)
    private Boolean editable = false;

//    @Column(name = "weather_info", nullable = false)
//    private String weatherInfo; // 날씨 API 결과 (TEXT)

    public OfficialEventDTO offEventDTO() {
        return new OfficialEventDTO(this.eventDate, this.eventTitle);
        // 유저들에게 이벤트 거는 건 레포지토리에서 추가 작업. + 신규 유저에게도 적용 필요함
    }
}