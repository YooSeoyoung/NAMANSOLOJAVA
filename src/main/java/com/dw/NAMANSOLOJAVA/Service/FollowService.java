package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    FollowRepository followRepository;
}
