package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.RecommendPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RecommendPlaceRepository extends JpaRepository<RecommendPlace, Long> {

    List<RecommendPlace> findByCity(String city);
    @Query("SELECT p FROM RecommendPlace p LEFT JOIN FETCH p.media")
    List<RecommendPlace> findAllWithMedia();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM recommend_place_media WHERE recommend_place_id = :placeId", nativeQuery = true)
    void deletePlaceMediaMapping(@Param("placeId") Long placeId);
}
