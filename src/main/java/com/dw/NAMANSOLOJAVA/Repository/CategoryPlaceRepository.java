package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.CategoryPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryPlaceRepository extends JpaRepository<CategoryPlace, Long> {
    List<CategoryPlace> findAllByCategory_Name(String name);
    List<CategoryPlace> findByRecommendPlace_CityAndCategory_Name(String city, String categoryName);
    void deleteByRecommendPlaceId(Long id);
}
