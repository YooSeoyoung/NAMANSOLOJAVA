package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.RecommendPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendPlaceRepository extends JpaRepository<RecommendPlace, Long> {

    Optional<RecommendPlace> findByName(String name);

}
