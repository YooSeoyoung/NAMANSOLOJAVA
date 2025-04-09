package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Repository.AlbumTagRepository;
import com.dw.NAMANSOLOJAVA.Repository.MediaRepository;
import com.dw.NAMANSOLOJAVA.Repository.TagRepository;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
import com.dw.NAMANSOLOJAVA.enums.Visibility;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.AlbumTag;
import com.dw.NAMANSOLOJAVA.model.Media;
import com.dw.NAMANSOLOJAVA.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    AlbumTagRepository albumTagRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    UserService userService;
    @Autowired
    TagRepository tagRepository;

    public List<AlbumDTO> getAllAlbum(){
        List<Album> albums = albumRepository.findAll();
      return albums.stream().map(album -> {
            List<AlbumTag> usertags = albumTagRepository.findByAlbumId(album.getId());
            return album.toAlbumDTO(usertags);
        }).toList();
    }
    public AlbumDTO getAlbumById(Long id){
       Album album= albumRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("존재하지 않는 Id입니다"));
       List<AlbumTag> usertags = albumTagRepository.findByAlbumId(id);
        return  album.toAlbumDTO(usertags);
    }
    @Transactional
    public UpdateAlbumDTO updateAlbum( UpdateAlbumDTO updateAlbumDTO){
        Album album = albumRepository.findById(updateAlbumDTO.getId()).orElseThrow(()->
                new ResourceNotFoundException("존재하지  않은 ID입니다"));
        album.setTitle(updateAlbumDTO.getTitle());
        album.setVisibility(Visibility.valueOf(updateAlbumDTO.getVisibility()));
        album.setMedia();
        album.setLatitude(updateAlbumDTO.getLatitude());
        album.setLongitude(updateAlbumDTO.getLongitude());
        album.setLocation(updateAlbumDTO.getLocation());
        album.setAddDate(LocalDateTime.now());

        List<Media> existmedia = album.getMedia();

        updateAlbumDTO.getMediaUrl().stream().map(mediaDTO -> mediaDTO.getId())
        mediaRepository.deleteById();
        return ;
    }
    @Transactional
    public AddAlbumDTO saveAlbum(AddAlbumDTO addAlbumDTO){
        List<Media> mediaList =addAlbumDTO.getMediaUrl().stream().map(
                mediaDTO -> new Media(
                        null,
                        mediaDTO.getMediaUrl(),
                        MediaType.valueOf(mediaDTO.getMediaType()))
        ).toList();
        mediaRepository.saveAll(mediaList);
        List<Tag> tagList = addAlbumDTO.getTagList().stream()
                .map(tag -> {
                    return tagRepository.findByName(tag.getName())
                            .orElseGet(() -> tagRepository.save(new Tag(null, tag.getName())));
                }).toList();
        Album album =new Album();
        album.setId(null);
        album.setTitle(addAlbumDTO.getTitle());
        album.setAddDate(LocalDateTime.now());
        album.setUser(userService.getCurrentUser());
        album.setLatitude(addAlbumDTO.getLatitude());
        album.setLongitude(addAlbumDTO.getLongitude());
        album.setLocation(addAlbumDTO.getLocation());
        album.setVisibility(Visibility.valueOf(addAlbumDTO.getVisibility().toUpperCase()));
        album.setMedia(mediaList);

        List<AlbumTag> albumTags = tagList.stream()
                .map(tag -> new AlbumTag(null,album, tag))
                .toList();
            albumTagRepository.saveAll(albumTags);
        return albumRepository.save(album).toAddAlbumDTO(tagList);
    }
    public String deleteAlbumById(Long id){
        return null;
    }
    public AlbumDTO getAlbumByUsername(String username){
        return null;
    }
}
