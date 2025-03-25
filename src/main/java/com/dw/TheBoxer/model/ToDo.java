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
@Table(name = "to_do")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title; // 제목(첫 데이트, 놀이공원 놀러간 날)

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 연애 시작일 등 기념일

    @Column(name = "last_date")
    private LocalDate lastDate; // 여행 일정 끝나는 날

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "memo")
    private String memo;

    @Column(name = "alert_sent", nullable = false)
    private Boolean alertSent; // 알람 발송 여부. 기본 false. 레포지토리에서 설정
}
