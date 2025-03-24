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
@Table(name = "user_calendar")
public class UserCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title; // 제목(첫 데이트, 놀이공원 놀러간 날)

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 연애 시작일 등 기념일

    @Column(name = "last_date")
    private LocalDate lastDate; // 여행 일정 끝나는 날
}
