package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Setter
@Getter
@Table(name = "user")
//UserDetails(security)의 구현체를 구현해서 override 하기
public class User implements UserDetails {
    @Id
    @Column(name="username", nullable = false, unique = true,updatable = false)
    private String username; // 유저명
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
    @Column(name = "birth_m", nullable = false, updatable = false)
    private LocalDate birthM; // 남자 생일
    @Column(name = "birth_f", nullable = false,updatable = false)
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
    @Column(name ="d_day", nullable = false)
    private  LocalDate dDay; //만난 날짜
    @Column(name ="last_login")
    private LocalDate lastLogin;
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
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    public UserDTO toUserDTO(){
        MediaDTO mediaDTO = this.media.toDTO();
        return new UserDTO(
                this.username,null,
                this.realNameM, this.realNameF,
                this.emailM,this.emailF,
                this.birthM, this.birthF,
                this.phoneNumberM, this.phoneNumberF,
                this.authority.getAuthorityName(), this.addDate,
                this.dDay, this.alarmAlert,
                this.commentAlert, this.followAlert,
                this.greatAlert, this.eventAlert,
                this.recommendAlert, this.recommentAlert,
                this.todoAlert, mediaDTO.getId(), // 널 들어가도 됨
                this.lastLogin
        );
    }

    public UpdateImageDDayDTO toUpdateImageDDayDTO(){
        return new UpdateImageDDayDTO(
                this.media.toDTO(),this.dDay
        );
    }

    public LoginDTO toLoginDTO(){
        return new LoginDTO(
                this.username,null
        );
    }

    public UserAddDateDTO toUserAddDateDTO(){
        return new UserAddDateDTO(
                this.username,this.addDate
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(authority.getAuthorityName()));
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // 만료 여부 : 초깃값은 true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // 잠겨있는지
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // 만료 여부 : 초깃값은 true
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
