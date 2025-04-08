package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AlbumDTO;
import com.dw.NAMANSOLOJAVA.DTO.AlbumTagDTO;
import com.dw.NAMANSOLOJAVA.Repository.AlbumTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumTagService {

    @Autowired
    AlbumTagRepository albumTagRepository;

    public List<AlbumTagDTO> SaveTagToAlbum(AlbumDTO albumDTO){
        return  null;
    }
    public List<AlbumTagDTO> getAllAlbumTagByAlbumId(Long albumId){
        return null;
    }

    public String  deleteAlbumTagById(Long id){
        return null;
    }

}
