package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.FollowDTO;
import com.dw.NAMANSOLOJAVA.DTO.FollowerDTO;
import com.dw.NAMANSOLOJAVA.DTO.FollowingDTO;
import com.dw.NAMANSOLOJAVA.Repository.FollowRepository;
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

    public List<FollowDTO> getAllFollow() {
        return null;
//        return followRepository.getAllFollower();
    }

    public FollowingDTO saveNewFollowing(FollowingDTO followingDTO) {
        return null;
//        return followRepository.saveNewFollowing(followingDTO);
    }

    public FollowerDTO saveNewFollower(FollowerDTO followerDTO) {
        return null;
//        return followRepository.saveNewFollower(followerDTO);
    }

    public List<FollowDTO> getAllFollowByUsername(String username) {
        return null;
//        return followRepository.getAllFollowByUsername(username);
    }

    public String deleteFollowing(Long id) {
        return null;
//        return followRepository.deleteFollowing(id);
    }

    public String deleteFollower(Long id) {
        return null;
//        return followRepository.deleteFollower(id);
    }


}
