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
    private User user; // 어떤 유저에게 보내는 알람


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AlarmType alarmType; // ENUM 알람 종류

    @Column(name ="message")
    private String message; // 알림 내용

    @Column(name ="add_date")
    private LocalDateTime addDate; // 알람 생성 시각

    @Column(name ="is_read")
    private boolean isRead = false; // 읽음 여부

    @Column(name = "icon_code")
    private String iconCode; // 날씨 아이콘

    // @Column(name = "weather_info")
    // private String weatherInfo;// 날씨 API 결과 (TEXT) 날씨 정보

    public AddAlarmDTO toAddAlarmDTO(){
        return new AddAlarmDTO(
                this.user.getUsername(), this.alarmType.name(),
                this.message, this.iconCode
        );
    }
    public AlarmDTO toAlarmDTO(){
        return new AlarmDTO(
                this.id, this.user.getUsername(),
                this.alarmType, this.message,
                this.addDate,this.isRead,
                this.iconCode
        );
    }
}
