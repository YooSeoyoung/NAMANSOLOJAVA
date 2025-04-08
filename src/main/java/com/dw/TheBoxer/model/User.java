package com.dw.TheBoxer.model;

import com.dw.TheBoxer.enums.Gender;
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
        @Column(name="username")
        private String username; // 유저명

        @Setter
        @Column(name="password", nullable = false)
        private String password; // 비밀번호


        @Column(name = "gender")
        @Enumerated(EnumType.STRING)
        private Gender gender; // ENUM

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

        @Column(name ="d_day")
        private  LocalDate dDay; //만난 날짜

        // 알람 발송 여부. 기본 true
        @Column(name = "alarm_alert", nullable = false)
         private Boolean alarmAlert = true;
        @Column(name = "comment_alert", nullable = false)
         private Boolean commentAlert= true;
        @Column(name = "follow_alert", nullable = false)
        private Boolean followAlert= true;
        @Column(name = "great_alert", nullable = false)
        private Boolean greatAlert= true;
        @Column(name = "event_alert", nullable = false)
        private Boolean eventAlert= true;
        @Column(name = "recommend_alert", nullable = false)
        private Boolean recommendAlert= true;
        @Column(name = "recomment_alert", nullable = false)
        private Boolean recommentAlert= true;
        @Column(name = "todo_alert", nullable = false)
        private Boolean todoAlert= true;
}