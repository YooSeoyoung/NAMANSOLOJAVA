package com.dw.NAMANSOLOJAVA.DTO;


import com.dw.NAMANSOLOJAVA.model.User;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String realNameM;
    private String realNameF;
    private String emailM;
    private String emailF;
    private LocalDate birthM;
    private LocalDate birthF;
    private String phoneNumberM;
    private String phoneNumberF;
    private String authority;
    private LocalDate addDate;
    private LocalDate dDay;
    private Boolean alarmAlert;
    private Boolean commentAlert;
    private Boolean followAlert;
    private Boolean greatAlert;
    private Boolean eventAlert;
    private Boolean recommendAlert;
    private Boolean recommentAlert;
    private Boolean todoAlert;
    private MediaDTO mediaUrl;
    private LocalDate lastLogin;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword(); // 민감하면 null 처리 가능
        this.realNameM = user.getRealNameM();
        this.realNameF = user.getRealNameF();
        this.emailM = user.getEmailM();
        this.emailF = user.getEmailF();
        this.birthM = user.getBirthM();
        this.birthF = user.getBirthF();
        this.phoneNumberM = user.getPhoneNumberM();
        this.phoneNumberF = user.getPhoneNumberF();
        this.addDate = user.getAddDate();
        this.dDay = user.getDDay();
        this.alarmAlert = user.getAlarmAlert();
        this.commentAlert = user.getCommentAlert();
        this.followAlert = user.getFollowAlert();
        this.greatAlert = user.getGreatAlert();
        this.eventAlert = user.getEventAlert();
        this.recommendAlert = user.getRecommendAlert();
        this.recommentAlert = user.getRecommentAlert();
        this.todoAlert = user.getTodoAlert();
        this.lastLogin = user.getLastLogin();
    }
}
