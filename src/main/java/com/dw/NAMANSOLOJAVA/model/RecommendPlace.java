package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceAdmDTO;
import com.dw.NAMANSOLOJAVA.DTO.RecommendPlaceDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "recommend_place")
public class RecommendPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // 장소 이름

    @Column(name = "address", nullable = false)
    private String address; // 주소 (지도 표시용)

    @Column(name = "city", nullable = false)
    private String city; // 도

    @Column(name = "latitude", nullable = false)
    private Double latitude; // 위도

    @Column(name = "longitude", nullable = false)
    private Double longitude; // 경도

    @Column(name = "description", length = 1000)
    private String description; // 상세 설명 (선택)

        @Column(name = "detail")
        private String detail;

        @OneToMany
        @JoinTable(
                name = "recommend_place_media", // 중간 테이블 이름
                joinColumns = @JoinColumn(name = "recommend_place_id"),     // album 외래키
                inverseJoinColumns = @JoinColumn(name = "media_id") // media 외래키
        )
        private List<Media> media = new ArrayList<>();

    public RecommendPlaceAdmDTO admDTO() {
        List<MediaDTO> mediaDTOs = media.stream()
                .map(Media::toDTO)
                .collect(Collectors.toList());

        return new RecommendPlaceAdmDTO(
                id,
                name,
                mediaDTOs,
                address,
                city,
                latitude,
                longitude,
                description,
                detail,
                null
        );
    }

    public RecommendPlaceDTO placeDTO() {
        List<MediaDTO> mediaDTO = media.stream().map(Media::toDTO).toList();
        return new RecommendPlaceDTO(
                this.name,mediaDTO,
                this.address, this.city,
                this.description, this.detail);
    }
}
