package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.*;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
import com.dw.NAMANSOLOJAVA.enums.Visibility;
import com.dw.NAMANSOLOJAVA.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    RecommentRepository recommentRepository;

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
        album.setLatitude(updateAlbumDTO.getLatitude());
        album.setLongitude(updateAlbumDTO.getLongitude());
        album.setLocation(updateAlbumDTO.getLocation());
        album.setAddDate(LocalDateTime.now());
        // 기존 미디어 목록 조회
        List<Media> existingMedia = album.getMedia(); // 기존 미디어들
        // 새 DTO로 받은 mediaId 목록 추출
        List<Long> newMedia = updateAlbumDTO.getMediaUrl().stream()
                .map(MediaDTO::getId).filter(Objects::nonNull).toList();
        // 삭제할 미디어 필터링 (기존인데, 새 목록에 없는 경우)
        List<Media> toDelete = existingMedia.stream()
                .filter(media -> !newMedia.contains(media.getId())).toList();
        // 삭제
        mediaRepository.deleteAll(toDelete);
        //유지할 + 새로 추가할 미디어 구성
        List<Media> finalMediaList = updateAlbumDTO.getMediaUrl().stream()
                .map(mediaDTO -> mediaRepository.findById(mediaDTO.getId())
                        .orElseGet(() -> new Media(
                                null,
                                mediaDTO.getMediaUrl(),
                                MediaType.valueOf(mediaDTO.getMediaType())
                        ))).toList();
        mediaRepository.saveAll(finalMediaList);
        album.setMedia(finalMediaList);

        //기존 앨범태그 목록 조회
        List<AlbumTag> existingTag =  albumTagRepository.findByAlbumId(album.getId());
        //새 DTO로 받을 tag
        List<Long> newTag = updateAlbumDTO.getTag().stream().map(Tag::getId).filter(Objects::nonNull).toList();
        //삭제할 앨범태그 필터링
        List<AlbumTag> deleteAlbumTag = existingTag.stream().filter(
                albumTag -> !newTag.contains(albumTag.getTag())).toList();
        albumTagRepository.deleteAll(deleteAlbumTag);
        //유지 및 새로 추가할 tag 및 앨범tag
        List<Tag> tagList = updateAlbumDTO.getTag().stream()
                .map(tag -> tagRepository.findByName(tag.getName())
                        .orElseGet(() -> tagRepository.save(new Tag(null, tag.getName())))
                ).toList();
        List<AlbumTag> newAlbumTags = tagList.stream()
                .map(tag -> new AlbumTag(null,album, tag))
                .toList();
        albumTagRepository.saveAll(newAlbumTags);

        return album.toUpdateAlbumDTO(tagList);
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
    @Transactional
    public String deleteAlbumById(Long id){
        List<Comment> comments =commentRepository.findByAlbumId(id);
        List<Long> commentId = comments.stream().map(Comment::getId).toList();
        List<ReComment> reComments =recommentRepository.findByCommentId(commentId);
        commentRepository.deleteAll(comments);
        recommentRepository.deleteAll(reComments);
        albumRepository.deleteById(id);
        return "정상 삭제되었습니다";
    }
    public List<AlbumDTO> getAlbumByUsername(String username){
        List<Album> albums = albumRepository.findByUsername(username);
        return albums.stream()
                .map(album -> {
                    List<AlbumTag> albumTags = albumTagRepository.findByAlbumId(album.getId());
                    return album.toAlbumDTO(albumTags);
                })
                .toList();
    }
}
