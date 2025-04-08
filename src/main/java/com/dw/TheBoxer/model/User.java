package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.PictureAndVideoDTO;
import com.dw.TheBoxer.DTO.UserDTO;
import com.dw.TheBoxer.DTO.UserUpdateDTO;
import com.dw.TheBoxer.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Setter
@Getter
@Table(name = "user")
public class User {
    @Id
    @Column(name="username", nullable = false, unique = true,updatable = false)
    private String username; // 유저명


    @Column(name="password", nullable = false)
    private String password; // 비밀번호


    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender; // ENUM


    @Column(name = "real_name_m", nullable = false)
    private String realNameM; // 남자 이름


    @Column(name = "real_name_f", nullable = false)
    private String realNameF; // 여자 이름


    @Column(name="email_m", nullable = false)
    private String emailM; // 남자 이메일


    @Column(name="email_f", nullable = false)
    private String emailF; // 여자 이메일


    @Column(name = "birth_m", nullable = false)
    private LocalDate birthM; // 남자 생일


    @Column(name = "birth_f", nullable = false)
    private LocalDate birthF; // 여자 생일


    @Column(name = "phone_number_m", nullable = false)
    private String phoneNumberM; // 남자 번호


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

    public UserUpdateDTO toUserUpdateDTO() {
        if (gender == Gender.MALE) {
            return new UserUpdateDTO(
                    this.gender.name(), this.realNameM,
                    this.emailM, this.phoneNumberM
            );
        } else if (gender == Gender.FEMALE) {
            return new UserUpdateDTO(
                    this.gender.name(), this.realNameF,
                    this.emailF, this.phoneNumberF
            );
        } else {
            // 예외 던지거나, 기본값 처리
            throw new IllegalStateException("Gender 정보가 없습니다.");
        }
    }

    public UserDTO toUserDTO(Media media){
        PictureAndVideoDTO pictureAndVideoDTO=media.toPictureAndVideoDTO();
        return new UserDTO(
                this.username,null,this.realNameM,
                this.realNameF,this.gender.name(),
                this.emailM,this.emailF,this.birthM,
                this.birthF,this.phoneNumberM,
                this.phoneNumberF,this.authority.getAuthorityName(),
                this.addDate,this.dDay,
                this.alarmAlert,this.commentAlert,
                this.followAlert,this.greatAlert,
                this.eventAlert,this.recommendAlert,
                this.recommentAlert,this.todoAlert,
                pictureAndVideoDTO
        );
    }
}