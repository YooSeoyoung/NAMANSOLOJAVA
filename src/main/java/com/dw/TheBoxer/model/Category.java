package com.dw.TheBoxer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "category")
public class Category {

    @Id
    private String name; // 장비, 기술, 루틴, 식단
}
