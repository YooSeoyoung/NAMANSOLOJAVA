package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name="user_name")
    private String userName; // 유저명

    @Column(name="password", nullable = false)
    private String password; // 비밀번호

    @Column(name = "real_name_m", nullable = false)
    private String realNameM; // 남자 이름

    @Column(name = "real_name_f", nullable = false)
    private String realNameF; // 여자 이름

    @Column(name="email_m", nullable = false)
    private String emailM; // 남자 이메일

    @Column(name="email_f", nullable = false)
    private String emailF; // 여자 이메일

    @Column(name = "phone_number_m", nullable = false)
    private String phoneNumberM; // 남자 번호

    @Column(name = "phone_number_f", nullable = false)
    private String phoneNumberF; // 여자 번호

    @OneToMany
    @JoinColumn(name = "user_schedule")
    private List<Schedule> schedules; // 기념일, 유저 한 명은 많은 기념일을 가지지만 같은 기념일을 가지진 않음

    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority; // 권한

    @Column(name = "add_date", updatable = false)
    private LocalDate addDate; // 회원가입일자
}