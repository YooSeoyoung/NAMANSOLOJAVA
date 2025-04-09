package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.AlbumTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumTagRepository extends JpaRepository<AlbumTag,Long> {

    List<AlbumTag> findByAlbumId(Long id);

}
