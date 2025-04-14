package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.RecommendPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecommendPlaceRepository extends JpaRepository<RecommendPlace, Long> {

    List<RecommendPlace> findByCity(String city);
    @Query("SELECT p FROM RecommendPlace p LEFT JOIN FETCH p.media")
    List<RecommendPlace> findAllWithMedia();

}
