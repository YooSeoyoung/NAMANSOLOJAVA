package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.enums.Visibility;
import com.dw.NAMANSOLOJAVA.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {

    List<Album> findByUser_UsernameAndVisibility(String username,Visibility visibility);


    List<Album> findByUser_Username(String username);
}
