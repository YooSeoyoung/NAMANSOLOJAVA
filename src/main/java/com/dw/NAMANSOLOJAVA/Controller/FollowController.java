package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.FollowDTO;
import com.dw.NAMANSOLOJAVA.DTO.FollowerDTO;
import com.dw.NAMANSOLOJAVA.DTO.FollowingDTO;
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

    @GetMapping("/all")
    public ResponseEntity<List<FollowDTO>> getAllFollow() {
        return new ResponseEntity<>(followService.getAllFollow(), HttpStatus.OK);
    }

    @PostMapping("/new/following")
    public ResponseEntity<FollowingDTO> saveNewFollowing(@RequestBody FollowingDTO followingDTO) {
        return new ResponseEntity<>(followService.saveNewFollowing(followingDTO), HttpStatus.CREATED);
    }

    @PostMapping("/new/follower")
    public ResponseEntity<FollowerDTO> saveNewFollower(@RequestBody FollowerDTO followerDTO) {
        return new ResponseEntity<>(followService.saveNewFollower(followerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<FollowDTO>> getAllFollowByUsername(@PathVariable String username) {
        return new ResponseEntity<>(followService.getAllFollowByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/delete/following/{id}")
    public ResponseEntity<String> deleteFollowing(@PathVariable Long id) {
        return new ResponseEntity<>(followService.deleteFollowing(id), HttpStatus.ACCEPTED);
    } // 팔로윙 해제

    @DeleteMapping("/delete/follower/{id}")
    public ResponseEntity<String> deleteFollower(@PathVariable Long id) {
        return new ResponseEntity<>(followService.deleteFollower(id), HttpStatus.ACCEPTED);
    } // 팔로우 해제
}
