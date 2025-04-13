package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    FollowService followService;

    @GetMapping("/search/all/{username}")
    public ResponseEntity<List<UserRelationDTO>> searchUsersWithRelation(@PathVariable String username) {
        return new ResponseEntity<>(followService.searchUsersWithRelation(username), HttpStatus.OK);
    }

    @GetMapping("/search/user-follower")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowerByUsername(@RequestParam(required = false) String username) {
        return new ResponseEntity<>(followService.getFollowerByUsername(username), HttpStatus.OK);
    }
    @GetMapping("/search/user-following")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowingByUsername(@RequestParam(required = false) String username) {
        return new ResponseEntity<>(followService.getFollowingByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/new/following")
    public ResponseEntity<FollowDTO> saveNewFollowing(@RequestBody FollowDTO followDTO) {
        return new ResponseEntity<>(followService.saveNewFollowing(followDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/following/{username}")
    public ResponseEntity<String> deleteFollowing(@PathVariable String username) {
        return new ResponseEntity<>(followService.deleteFollowing(username), HttpStatus.ACCEPTED);
    } // 팔로윙 해제

    @DeleteMapping("/delete/follower/{username}")
    public ResponseEntity<String> deleteFollower(@PathVariable String username) {
        return new ResponseEntity<>(followService.deleteFollower(username), HttpStatus.ACCEPTED);
    } // 팔로우 해제


    @GetMapping("/all/followers")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowers() {
        return new ResponseEntity<>(followService.getFollowers(), HttpStatus.OK);
    }
    @GetMapping("/all/followings")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowings() {
        return new ResponseEntity<>(followService.getFollowings(), HttpStatus.OK);
    }
}
