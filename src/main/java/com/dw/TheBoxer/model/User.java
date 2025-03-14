package com.dw.TheBoxer.model;

import com.dw.TheBoxer.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name="user_name")
    private String userName;

    @Setter
    @Column(name = "real_name", nullable = false)
    private String realName;

    @Setter
    @Column(name="password", nullable = false)
    private String password;

    @Setter
    @Column(name="email", nullable = false)
    private String email;

    @Setter
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender; // ENUM 수정 필요

    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority; // 권한

    @OneToMany(mappedBy = "sender")
    private List<FriendRequest> sendRequests = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<FriendRequest> receivedRequests = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> friends;





}
