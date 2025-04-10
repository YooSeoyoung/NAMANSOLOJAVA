package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.OfficialEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OfficialEventRepository extends JpaRepository<OfficialEvent, Long> {
    @Query("SELECT o FROM OfficialEvent o WHERE o.user.username = :username")
    List<OfficialEvent> findAllByUsername(String username);
    Optional<OfficialEvent> findByIdAndUsername(Long id, String username);
}
