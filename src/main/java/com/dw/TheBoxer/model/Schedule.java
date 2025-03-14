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
@Table(name = "schedule")
public class Schedule { //일정표

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", updatable = false)
    private LocalDate startDate;//복싱 관련 일정 시작일

    @Column(name = "last_date", updatable = false)
    private LocalDate lastDate; //일정이 끝나는 날짜 2025-01-01

    @Column(name = "title")
    private String title; // 제목(단증 취득 시기, 2025년 청소년 아마추어 대회 )

    @Column(name = "content")
    private String content; //단증 취득 같은 경우 각 지역마다 시행하는 장소는 복싱협회 사이트에서 확인이 가능합니다
}
