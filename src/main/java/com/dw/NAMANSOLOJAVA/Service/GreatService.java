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

import java.util.List;
import java.util.Optional;

@Service
public class GreatService {
    @Autowired
    GreatRepository greatRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    UserService userService;
    @Autowired
    AlarmService alarmService;

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
        if (liked && !user.getUsername().equals(album.getUser().getUsername())) {
            alarmService.sendGreatAlarm(album.getUser().getUsername(), user.getUsername());
        }
        return new GreatToggleResultDTO(album.getId(), liked, user.getUsername());
    }

    public List<String> getAllGreats(Long albumId){
        Album album = albumRepository.findById(albumId).orElseThrow(()-> new ResourceNotFoundException("존재하지 않은 앨범ID입니다"));
      return   greatRepository.findByAlbum(album).stream().map(great -> great.getUser().getUsername()).toList();
    }

}
