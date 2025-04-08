package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.enums.Visibility;
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
@Table(name = "album")
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

    @Column(name = "location", nullable = false)
    private String location; // 주소 (지도 표시용)

    @Column(name = "visibility", nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibility visibility; // 피드는 유저 개인의 것이니 다른 사람과 기본 공유 설정은 안 됨.

    @OneToMany(mappedBy = "album")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    private List<Great> greats = new ArrayList<>();

    public UpdateAlbumDTO toAddOrUpdateAlbumDTO(List<Media> mediaList, List<AlbumTag> tagList){
        List<PictureAndVideoDTO> mediaDTOs= mediaList.stream().map(Media::toPictureAndVideoDTO).toList();
        return  new UpdateAlbumDTO(
                this.id, this.title, this.visibility.name(),
                mediaDTOs,tagList,this.latitude,
                this.longitude, this.location
        );
    }
    public AddAlbumDTO toAddAlbumDTO(List<Media> mediaList, List<Tag> tagList){
        List<PictureAndVideoDTO> mediaDTOs= mediaList.stream().map(Media::toPictureAndVideoDTO).toList();
        return  new AddAlbumDTO(
               this.title, this.visibility.name(),
                mediaDTOs,tagList,this.latitude,
                this.longitude, this.location
        );
    }

    public AlbumDTO toAlbumDTO(List<Media> mediaList,List<AlbumTag> albumTagList) {
        List<PictureAndVideoDTO> mediaDTOs= mediaList.stream().map(Media::toPictureAndVideoDTO).toList();
        List<CommentDTO> commentDTOList= comments.stream().map(Comment::toCommentDTO).toList();
        List<String> greatList = greats.stream().map(great -> great.getUser().getUsername()).toList();
        List<String> userTagList = albumTagList.stream().map(albumTag -> albumTag.getTag().getName()).toList();
        return new AlbumDTO(
                this.id, this.title, this.addDate,
                this.user.getUsername(), this.visibility.name(),
                userTagList,
                commentDTOList,greatList,
                mediaDTOs, this.location
        );
    }
}
