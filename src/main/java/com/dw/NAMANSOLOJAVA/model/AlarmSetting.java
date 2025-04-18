package com.dw.NAMANSOLOJAVA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alarm_setting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    private String follow ;
    private String comment ;
    private String great;
    private String recomment;
    private String todo ;
    private String weather ;
}