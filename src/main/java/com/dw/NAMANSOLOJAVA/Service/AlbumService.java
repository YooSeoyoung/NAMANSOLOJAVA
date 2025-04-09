package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AddAlbumDTO;
import com.dw.NAMANSOLOJAVA.DTO.UpdateAlbumDTO;
import com.dw.NAMANSOLOJAVA.DTO.AlbumDTO;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Repository.AlbumTagRepository;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.AlbumTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    AlbumTagRepository albumTagRepository;

    public List<AlbumDTO> getAllAlbum(){
        return null;
    }
    public AlbumDTO getAlbumById(Long id){
       Album album= albumRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("존재하지 않는 Id입니다"));
       List<AlbumTag> usertags = albumTagRepository.findByAlbumId(id);
        return  album.toAlbumDTO(usertags);
    }
    public UpdateAlbumDTO updateAlbum( UpdateAlbumDTO updateAlbumDTO){
        return null;
    }
    public AddAlbumDTO saveAlbum(AddAlbumDTO updateAlbumDTO){
        return null;
    }
    public String deleteAlbumById(Long id){
        return null;
    }
    public AlbumDTO getAlbumByUsername(String username){
        return null;
    }
}
