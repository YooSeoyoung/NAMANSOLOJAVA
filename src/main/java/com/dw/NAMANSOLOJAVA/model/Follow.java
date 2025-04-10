package com.dw.NAMANSOLOJAVA.model;


import com.dw.NAMANSOLOJAVA.DTO.FollowDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_name", nullable = false)
    private User follower; // 팔로우를 하는 사람

    @ManyToOne
    @JoinColumn(name = "following_name", nullable = false)
    private User following; //팔로우를 당하는 사람

//    @Column(name = "add_time", nullable = false)
//    private LocalDateTime addTime; // 생성시간 지정 -> 유저 요청시간, 레포지토리에 매핑 LocalDateTime.now();

    public FollowDTO toFollowDTO(){
        return new FollowDTO(
                this.id,this.follower.getUsername(),
                this.following.getUsername()
        );
    }
}
