package com.dw.NAMANSOLOJAVA.model;


import com.dw.NAMANSOLOJAVA.DTO.AddAlarmDTO;
import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AlarmType alarmType; // ENUM

    @Column(name ="message")
    private String message; // 알림 내용

    @Column(name ="add_date")
    private LocalDateTime addDate;

    @Column(name ="is_read")
    private boolean isRead = false; // 읽음 여부

    @Column(name = "weather_info")
    private String weatherInfo; // 날씨 API 결과 (TEXT)

    public AddAlarmDTO toAddAlarmDTO(){
        return new AddAlarmDTO(
                this.user.getUsername(), this.alarmType.name(),
                this.message, this.weatherInfo
        );
    }
    public AlarmDTO toAlarmDTO(){
        return new AlarmDTO(
                this.id, this.user.getUsername(),
                this.alarmType.name(), this.message,
                this.addDate,this.isRead,
                this.weatherInfo
        );
    }
}
