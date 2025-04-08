package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.OfficialEventDTO;
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

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate; // 기념일 날짜

    @Column(name = "event_type", nullable = false)
    private String eventType; // 발렌타인, 크리스마스 등

    @Column(name = "editable", nullable = false)
    private Boolean editable = false;

//    @Column(name = "weather_info", nullable = false)
//    private String weatherInfo; // 날씨 API 결과 (TEXT)

    public OfficialEventDTO offEventDTO() {
        return new OfficialEventDTO(this.eventType, this.eventDate);
        // 유저들에게 이벤트 거는 건 레포지토리에서 추가 작업. + 신규 유저에게도 적용 필요함
    }
}