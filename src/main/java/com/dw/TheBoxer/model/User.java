package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Setter
    @Column(name="user_name")
    private String userName; // 유저명

    @Setter
    @Column(name="password", nullable = false)
    private String password; // 비밀번호

    @Setter
    @Column(name = "real_name_m", nullable = false)
    private String realNameM; // 남자 이름

    @Setter
    @Column(name = "real_name_f", nullable = false)
    private String realNameF; // 여자 이름

    @Setter
    @Column(name="email_m", nullable = false)
    private String emailM; // 남자 이메일

    @Setter
    @Column(name="email_f", nullable = false)
    private String emailF; // 여자 이메일

    @Setter
    @Column(name = "birth_m", nullable = false)
    private LocalDate birthM; // 남자 생일

    @Setter
    @Column(name = "birth_f", nullable = false)
    private LocalDate birthF; // 여자 생일

    @Setter
    @Column(name = "phone_number_m", nullable = false)
    private String phoneNumberM; // 남자 번호

    @Setter
    @Column(name = "phone_number_f", nullable = false)
    private String phoneNumberF; // 여자 번호

    @ManyToOne
    @JoinColumn(name = "ROLE_authority", nullable = false)
    private Authority authority; // 권한

    @Column(name = "add_date", updatable = false)
    private LocalDate addDate; // 회원가입일자
}