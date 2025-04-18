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

import java.util.Collections;
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
    @Autowired
    AlarmService alarmService;

    public List<UserRelationDTO> searchUsersWithRelation(String username) {
        User currentUser = userService.getCurrentUser();

        List<User> users = userRepository.findByUsernameLike("%"+username+"%").stream()
                .filter(u -> !u.getUsername().equals(currentUser.getUsername()))
                .filter(user -> !user.getAuthority().getAuthorityName().equals("ROLE_ADMIN"))
                .toList();
        List<String> followingUsernames = followRepository.findByFollower(currentUser).stream()
                .map(f -> f.getFollowing().getUsername()).toList();        // 내가 팔로우한 유저(즉, 팔로잉찾기)
        List<String> followerUsernames = followRepository.findByFollowing(currentUser).stream()
                .map(f -> f.getFollower().getUsername()).toList();        // 나를 팔로우한 유저(즉, 팔로워찾기)
        return users.stream().map(user -> {
            String followname = user.getUsername();
            String profileUrl = user.getMedia().getMediaUrl();
            boolean isFollowing = followingUsernames.contains(followname); //팔로잉 목록에 있으면 true
            boolean isFollower = followerUsernames.contains(followname); // 팔로워 목록에 있으면 true
            String relation;
            if (isFollowing && isFollower) {
                relation = "FRIEND"; // 양쪽에 다 이름이 있으면 상호 팔로우
            } else if (isFollowing) {  //내가 팔로우 한 사람에만 있으면
                relation = "FOLLOWING";
            } else if (isFollower) { //나를 팔로우한 사람에만 있으면
                relation = "FOLLOWER";
            } else {
                relation = "NONE"; // 아무 관계도 아님
            }
            return new UserRelationDTO(followname, profileUrl, relation);
        }).toList();
    }

    public List<UserFollowInfoDTO> getFollowerByUsername(String username){
        if (username == null || username.trim().isEmpty()) {
            return getFollowers();
        }
        User user = userService.getCurrentUser();
        List<User> followerUser = userRepository.findByUsernameLike("%"+username+"%").stream().filter(
                user1 -> !user1.getUsername().equals(user.getUsername())).toList();

        List<Follow> following = followRepository.findByFollower(user);

        return followerUser.stream().filter(follower ->followRepository.findByFollowerAndFollowing(follower,user).isPresent())
                .map(follower -> {
                    String profileUrl =  follower.getMedia().getMediaUrl();
                    boolean isMutualFollow = following.stream().map(
                            f -> f.getFollowing().getUsername()
                    ).toList().contains(follower.getUsername());
                    return new UserFollowInfoDTO(follower.getUsername(), profileUrl,isMutualFollow);
                })
                .toList();
    }
    public List<UserFollowInfoDTO> getFollowingByUsername(String username){
        if (username == null || username.trim().isEmpty()) {
            return getFollowings();
        }
        User user = userService.getCurrentUser();
        List<User> followingUser = userRepository.findByUsernameLike("%"+username+"%").stream().filter(
                user1 -> !user1.getUsername().equals(user.getUsername())).toList();

        List<Follow> followers=followRepository.findByFollowing(user);

        return followingUser.stream().filter(following ->followRepository.findByFollowerAndFollowing(user,following).isPresent())
                .map(following -> {
                    String profileUrl =  following.getMedia().getMediaUrl();
                    boolean isMutualFollow = followers.stream().map(
                            f -> f.getFollower().getUsername()
                    ).toList().contains(following.getUsername());
                    return new UserFollowInfoDTO(following.getUsername(), profileUrl,isMutualFollow);
                })
                .toList();
    }

    public List<UserFollowInfoDTO> getFollowers() {
        User user = userService.getCurrentUser();
        List<Follow> followers=followRepository.findByFollowing(user);
        List<Follow> following = followRepository.findByFollower(user);

       return followers.stream().map(
                follow -> {
                    User follower = follow.getFollower();
                    String profileUrl = follow.getFollower().getMedia().getMediaUrl();

                    boolean isMutualFollow = following.stream().map(
                            f -> f.getFollowing().getUsername()
                    ).toList().contains(follower.getUsername());
                    return new UserFollowInfoDTO(follower.getUsername(), profileUrl,isMutualFollow);
                })
                .toList();
        }

    public List<UserFollowInfoDTO> getFollowings() {
        User user = userService.getCurrentUser();
       List<Follow> followings= followRepository.findByFollower(user);
        List<Follow> followers=followRepository.findByFollowing(user);

       return  followings.stream().map(
               follow -> {
                   User following = follow.getFollowing();
                   String profileUrl = follow.getFollowing().getMedia().getMediaUrl();
                   boolean isMutualFollow = followers.stream().map(
                           f -> f.getFollower().getUsername()
                   ).toList().contains(following.getUsername());

                return new UserFollowInfoDTO(following.getUsername(),profileUrl,isMutualFollow);
               }).toList();
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
        FollowDTO result = followRepository.save(follow).toFollowDTO();
        alarmService.sendFollowAlarm(following.getUsername(), user.getUsername());

        return result;
    }

}
