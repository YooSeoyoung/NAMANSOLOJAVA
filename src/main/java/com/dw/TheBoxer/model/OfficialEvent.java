package com.dw.TheBoxer.model;

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
    @JoinColumn(name = "user_name")
    private User user;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate; // 기념일 날짜

    @Column(name = "event_type", nullable = false)
    private String eventType; // 발렌타인, 크리스마스 등

//    @Column(name = "weather_info", nullable = false)
//    private String weatherInfo; // 날씨 API 결과 (TEXT)

}