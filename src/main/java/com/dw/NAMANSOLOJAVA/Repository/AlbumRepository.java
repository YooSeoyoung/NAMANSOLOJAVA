package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.DTO.AlbumDailyDTO;
import com.dw.NAMANSOLOJAVA.DTO.AlbumRankDTO;
import com.dw.NAMANSOLOJAVA.enums.Visibility;
import com.dw.NAMANSOLOJAVA.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {

    List<Album> findByUser_UsernameAndVisibility(String username,Visibility visibility);


    List<Album> findByUser_Username(String username);

    @Query("SELECT new com.dw.NAMANSOLOJAVA.DTO.AlbumDailyDTO(" +
            "FUNCTION('DATE', a.addDate), COUNT(a)) " +
            "FROM Album a " +
            "WHERE a.addDate BETWEEN :from AND :to " +
            "GROUP BY FUNCTION('DATE', a.addDate)")
    List<AlbumDailyDTO> countDailyFeeds(@Param("from") LocalDateTime from,
                                        @Param("to") LocalDateTime to);



    @Query("SELECT new com.dw.NAMANSOLOJAVA.DTO.AlbumRankDTO(" +
            "a.user.username, COUNT(a)) " +
            "FROM Album a WHERE FUNCTION('DATE_FORMAT', a.addDate, '%Y-%m') = :month " +
            "GROUP BY a.user.username ORDER BY COUNT(a) DESC")
    List<AlbumRankDTO> countFeedsByUserPerMonth(@Param("month") String month);


}
