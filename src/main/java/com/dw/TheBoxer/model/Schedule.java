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

    @Column(name = "start_date")
    private LocalDate startDate; // 연애 시작일 등 기념일

    @Column(name = "title")
    private String title; // 제목(첫 데이트, 놀이공원 놀러간 날)
}