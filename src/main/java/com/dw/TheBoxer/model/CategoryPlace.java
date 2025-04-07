package com.dw.TheBoxer.model;

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
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "recommend_place")
    private RecommendPlace recommendPlace;

//    @ManyToOne
//    @JoinColumn(name = "event_place")
//    private EventPlace eventPlace;
}
