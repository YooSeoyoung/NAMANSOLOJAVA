package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.GreatDTO;
import com.dw.NAMANSOLOJAVA.DTO.GreatToggleResultDTO;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Repository.GreatRepository;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.Great;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreatService {
    @Autowired
    GreatRepository greatRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    UserService userService;

    @Transactional
    public GreatToggleResultDTO toggleGreat(GreatDTO greatDTO){
        User user = userService.getCurrentUser();
        Album album = albumRepository.findById(greatDTO.getAlbumId())
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않은 앨범 Id입니다"));

        Optional<Great> existingGreat = greatRepository.findByUserAndAlbum(user, album);

        boolean liked;
        if (existingGreat.isPresent()) {
            greatRepository.delete(existingGreat.get());
            liked = false;
        } else {
            Great newGreat = new Great(null, album, user);
            greatRepository.save(newGreat);
            liked = true;
        }

        return new GreatToggleResultDTO(album.getId(), liked, user.getUsername());
    }

//    public String deleteGreat(GreatDTO greatDTO){
//            User user = userService.getCurrentUser();
//            Great great = greatRepository.findById(greatDTO.getId()).orElseThrow(()-> new ResourceNotFoundException())
//        return null;
//    }
}
