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
    private ResponseEntity<List<FollowDTO>> getAllFollow() {
        return new ResponseEntity<>(followService.getAllFollow(), HttpStatus.OK);
    }

    @PostMapping("/new/following")
    private ResponseEntity<FollowingDTO> saveNewFollowing(@RequestBody FollowingDTO followingDTO) {
        return new ResponseEntity<>(followService.saveNewFollowing(followingDTO), HttpStatus.CREATED);
    }

    @PostMapping("/new/follower")
    private ResponseEntity<FollowerDTO> saveNewFollower(@RequestBody FollowerDTO followerDTO) {
        return new ResponseEntity<>(followService.saveNewFollower(followerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all/{username}")
    private ResponseEntity<List<FollowDTO>> getAllFollowByUsername(@PathVariable String username) {
        return new ResponseEntity<>(followService.getAllFollowByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/delete/following/{id}")
    private ResponseEntity<String> deleteFollowing(@PathVariable Long id) {
        return new ResponseEntity<>(followService.deleteFollowing(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/follower/{id}")
    private ResponseEntity<String> deleteFollower(@PathVariable Long id) {
        return new ResponseEntity<>(followService.deleteFollower(id), HttpStatus.ACCEPTED);
    }

//    @PutMapping("/")
}
