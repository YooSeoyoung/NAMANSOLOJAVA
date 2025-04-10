package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.EventPresent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventPresentRepository extends JpaRepository<EventPresent, Long> {
    List<EventPresent> findAllByTitleContainingIgnoreCase(String keyword);
}
