package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.AnniversaryDTO;
import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoAllDTO;
import com.dw.NAMANSOLOJAVA.DTO.ToDoTravelDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "final_edit_date", nullable = false)
    private LocalDate finalEditDate; // 해당 일정에 대한 추가/수정 등 최종 수정 시간

//    @Column(name = "memo")
//    private String memo;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "editable", nullable = false)
    private Boolean editable = true;

    @OneToMany
    @JoinTable(
            name = "todo_media", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "todo_id"),     // album 외래키
            inverseJoinColumns = @JoinColumn(name = "media_id") // media 외래키
    )
    private List<Media> media = new ArrayList<>();

    public AnniversaryDTO toAnniDTO() {
        return new AnniversaryDTO(this.id, this.title,
                this.startDate, this.lastDate,
                this.color, this.type,
                this.editable);
    }

    public ToDoTravelDTO toTravelDTO() {
        List<MediaDTO> mediaDTO = media.stream().map(Media::toDTO).toList();
        return new ToDoTravelDTO(this.id, this.title,
                this.startDate, this.lastDate,
                mediaDTO, this.color,
                this.type, this.editable);
    }

    public ToDoAllDTO allDTO() {
        List<MediaDTO> mediaDTO = media.stream().map(Media::toDTO).toList();
        return new ToDoAllDTO(this.id, this.title,
                this.startDate, this.lastDate,
                mediaDTO, this.color,
                this.type, this.editable);
    }
}
