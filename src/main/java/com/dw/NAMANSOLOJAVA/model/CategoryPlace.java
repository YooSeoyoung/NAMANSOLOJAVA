package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.CategoryPlaceDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "category_place")
public class CategoryPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_name")
    @ToString.Exclude
    private Category category;

    @ManyToOne
    @JoinColumn(name = "recommend_place_id")
    @ToString.Exclude
    private RecommendPlace recommendPlace;

//    @ManyToOne
//    @JoinColumn(name = "event_place")
//    private EventPlace eventPlace;

    public CategoryPlaceDTO toDTO() {
        return new CategoryPlaceDTO( recommendPlace.getId(), List.of(category.getName()));
    }

    public CategoryPlace(Category category, RecommendPlace recommendPlace) {
        this.category = category;
        this.recommendPlace = recommendPlace;
    }
}
