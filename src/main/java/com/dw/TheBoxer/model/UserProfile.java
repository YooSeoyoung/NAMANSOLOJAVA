package com.dw.TheBoxer.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user; // 하나의 유저가 많은 userProfile을 가질 수 있음

//    @Column(name = "preferred_equipment")
//    private String preferredEquipment ="";  // 선호 장비 (예: 복싱 글러브, 샌드백 등) -> board에 title 기준
//
//    @Column(name = "preferred_technique")
//    private String preferredTechnique="";  // 선호 기술 (예: 잽, 어퍼컷, 훅 등)  -> board에 title 기준
//
//    @Column(name = "preferred_diet")
//    private String preferredDiet ="";  //  -> board에 category 기준(식단)
//
//    @Column(name = "preferred_routine")
//    private String preferredRoutine ="";  //-> board에 category 기준(루틴)
@ManyToMany
@JoinTable(
        name = "user_profile_tag",
        joinColumns = @JoinColumn(name = "user_profile_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
)
private List<Tag> preferredTags = new ArrayList<>();  // 유저가 선호하는 태그 목록
    //하나의 유저가 여러 태그를 선호할 수 있고, 동시에 하나의 태그가 여러 유저에게 선호될 수 있음
}
