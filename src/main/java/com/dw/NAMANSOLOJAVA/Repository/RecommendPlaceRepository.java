package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.RecommendPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendPlaceRepository extends JpaRepository<RecommendPlace, Long> {

    List<RecommendPlace> findByCity(String city);

}
