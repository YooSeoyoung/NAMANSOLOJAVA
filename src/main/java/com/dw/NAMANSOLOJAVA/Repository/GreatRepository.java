package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.Great;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GreatRepository extends JpaRepository<Great,Long> {

    void deleteByAlbumId(Long id);

    Optional<Great> findByUserAndAlbum(User user, Album album);
}
