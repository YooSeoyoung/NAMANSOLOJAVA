package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.MediaDTO;
import com.dw.NAMANSOLOJAVA.DTO.PictureAndVideoDTO;
import com.dw.NAMANSOLOJAVA.Repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MediaService {
    @Autowired
    MediaRepository mediaRepository;

    public MediaDTO saveSingleMedia(MediaDTO mediaDTO) {
        return null;
//        return mediaRepository.saveSingleMedia(mediaDTO);
    }

    public List<MediaDTO> saveMultipleMedia(List<MediaDTO> mediaDTOs) {
        return null;
//        return mediaRepository.saveMultipleMedia(mediaDTOs);
    }

    public List<MediaDTO> getMultipleMedia() {
        return null;
//        return mediaRepository.getMultipleMedia();
    }

    public MediaDTO deleteSingleMediaById(Long id) {
        return null;
//        return mediaRepository.deleteSingleMediaById(id);
    }
}