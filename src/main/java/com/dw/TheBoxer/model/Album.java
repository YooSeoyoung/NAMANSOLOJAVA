package com.dw.TheBoxer.model;

import com.dw.TheBoxer.DTO.AddOrUpdateAlbumDTO;
import com.dw.TheBoxer.DTO.AlbumDTO;
import com.dw.TheBoxer.DTO.PictureAndVideoDTO;
import com.dw.TheBoxer.enums.Visibility;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "story")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user; // 유저 개인은 피드를 생성함.

    @Column(name = "latitude", nullable = false)
    private Double latitude; // 위도

    @Column(name = "longitude", nullable = false)
    private Double longitude; // 경도

    @Column(name = "address", nullable = false)
    private String address; // 주소 (지도 표시용)

    @Column(name = "visibility", nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibility visibility; // 피드는 유저 개인의 것이니 다른 사람과 기본 공유 설정은 안 됨.

    @OneToMany(mappedBy = "story")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "story")
    private List<Great> greats = new ArrayList<>();

    public AddOrUpdateAlbumDTO toAddOrUpdateAlbumDTO(List<Media> mediaList){
        List<PictureAndVideoDTO> mediaDTOs= mediaList.stream().map(Media::toPictureAndVideoDTO).toList();
        return  new AddOrUpdateAlbumDTO(
                this.title, this.visibility.name(),
                mediaDTOs
        );
    }
}
