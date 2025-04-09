package com.dw.NAMANSOLOJAVA.model;

import com.dw.NAMANSOLOJAVA.DTO.CategoryPlaceDTO;
import jakarta.persistence.*;
import lombok.*;

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
    private Category category;

    @ManyToOne
    @JoinColumn(name = "recommend_place_id")
    private RecommendPlace recommendPlace;

//    @ManyToOne
//    @JoinColumn(name = "event_place")
//    private EventPlace eventPlace;

    public CategoryPlaceDTO toDTO() {
        return new CategoryPlaceDTO(category.toDTO(), recommendPlace.getId());
    }
}
