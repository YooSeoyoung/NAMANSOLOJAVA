package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Repository.AlbumTagRepository;
import com.dw.NAMANSOLOJAVA.Repository.FollowRepository;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.enums.Visibility;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.AlbumTag;
import com.dw.NAMANSOLOJAVA.model.Follow;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    FollowRepository followRepository;
    @Autowired
    UserService userService;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    AlbumTagRepository albumTagRepository;
    @Autowired
    UserRepository userRepository;

    public List<FollowDTO> getAllFollow() {
        User user = userService.getCurrentUser();

        return followRepository.findAll().stream().map(Follow::toFollowDTO).toList();
    }
    public List<FollowDTO> getAllFollowByUsername(String username) {
        return null;
    }

    public List<FollowDTO> getSearchResultByName(String username) {
        return null;
    }

    public List<UserFollowInfoDTO> getFollowerByUsername(String username){
        User user = userService.getCurrentUser();
        List<User> followerUser = userRepository.findByUsernameContaining(username).stream().filter(
                user1 -> !user1.getUsername().equals(user.getUsername())).toList();
        return followerUser.stream().filter(follower ->followRepository.findByFollowerAndFollowing(follower,user).isPresent())
                .map(follower -> {
                    String profileUrl =  follower.getMedia().getMediaUrl();
                    return new UserFollowInfoDTO(follower.getUsername(), profileUrl);
                })
                .toList();
    }
    public List<UserFollowInfoDTO> getFollowingByUsername(String username){
        User user = userService.getCurrentUser();
        List<User> followingUser = userRepository.findByUsernameContaining(username).stream().filter(
                user1 -> !user1.getUsername().equals(user.getUsername())).toList();
        return followingUser.stream().filter(following ->followRepository.findByFollowerAndFollowing(user,following).isPresent())
                .map(following -> {
                    String profileUrl =  following.getMedia().getMediaUrl();
                    return new UserFollowInfoDTO(following.getUsername(), profileUrl);
                })
                .toList();
    }


    public String deleteFollowing(String username) { //내가 팔로우한 사람 삭제(팔로우 취소)
        User user = userService.getCurrentUser();
        User followingUser = userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 사용자입니다."));
        Follow follow = followRepository.findByFollowerAndFollowing(user, followingUser)
                .orElseThrow(() -> new ResourceNotFoundException("팔로우 관계가 존재하지 않습니다."));

        followRepository.delete(follow);
        return "팔로우를 취소했습니다.";
    }

    public String deleteFollower(String username) {
        User user = userService.getCurrentUser();
        User followerUser = userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 사용자입니다."));
        Follow follow = followRepository.findByFollowerAndFollowing(followerUser,user)
                .orElseThrow(() -> new ResourceNotFoundException("팔로우 관계가 존재하지 않습니다."));

        followRepository.delete(follow);
        return "팔로잉을 해제하였습니다";
    }

    public FollowDTO saveNewFollowing(FollowDTO followDTO) { // 새로운 사람을 내가 팔로우하기
        User user = userService.getCurrentUser();
        User following =  userRepository.findById(followDTO.getFollowing()).orElseThrow(()-> new ResourceNotFoundException("존재하지 않은 username입니다"));
        if (followRepository.existsByFollowerAndFollowing(user,following)){
            throw new IllegalArgumentException("이미 팔로우 중입니다");
        }
        Follow follow= new Follow(
                null,
                user,
                following
        );
        return followRepository.save(follow).toFollowDTO();
    }

    public List<UserFollowInfoDTO> getFollowers() {
        User user = userService.getCurrentUser();
        List<Follow> followers=followRepository.findByFollowing(user);

       return followers.stream().map(
                follow -> {
                    User follower = follow.getFollower();
                    String profileUrl = follow.getFollower().getMedia().getMediaUrl();
//                    List<Album> albums = albumRepository.findByUser_UsernameAndVisibility(follower.getUsername(), Visibility.PUBLIC);
//                    List<AlbumDTO> albumDTOS= albums.stream().map(
//                            album -> {
//                                List<AlbumTag> albumTags =  albumTagRepository.findByAlbumId(album.getId());
//                                return album.toAlbumDTO(albumTags);
//                            }).toList();
                    return new UserFollowInfoDTO(follower.getUsername(), profileUrl);
                })
                .toList();
        }

    public List<UserFollowInfoDTO> getFollowings() {
        User user = userService.getCurrentUser();
       List<Follow> followings= followRepository.findByFollower(user);

       return  followings.stream().map(
               follow -> {
                   User following = follow.getFollowing();
                   String profileUrl = follow.getFollowing().getMedia().getMediaUrl();
//
//                   List<Album> albums = albumRepository.findByUser_UsernameAndVisibility(following.getUsername(), Visibility.PUBLIC);
//                    List<AlbumDTO> albumDTOS = albums.stream().map(
//                            album -> {
//                                List<AlbumTag> albumTags = albumTagRepository.findByAlbumId(album.getId());
//                                return album.toAlbumDTO(albumTags);
//                            }).toList();
                return new UserFollowInfoDTO(following.getUsername(),profileUrl);
               }).toList();
            }
}
