package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.MediaDTO;
import com.dw.TheBoxer.DTO.ToDoDTO;
import com.dw.TheBoxer.DTO.ToDoTravelDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; // 제목(첫 데이트, 놀이공원 놀러간 날)

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 연애 시작일 등 기념일

    @Column(name = "last_date", nullable = false)
    private LocalDate lastDate; // 여행 일정 끝나는 날

//    @Column(name = "memo")
//    private String memo;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    public ToDoDTO todoDTO(List<MediaDTO> mediaDTOS) {
        return new ToDoDTO(this.title, this.startDate,
                mediaDTOS, this.type);
    }

    public ToDoTravelDTO toTravelDTO(List<MediaDTO> mediaDTOS) {
        return new ToDoTravelDTO(this.title, this.startDate,
                this.lastDate, mediaDTOS
                , this.type);
    }
}
