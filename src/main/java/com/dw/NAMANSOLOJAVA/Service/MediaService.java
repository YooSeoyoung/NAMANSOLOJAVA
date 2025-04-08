package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
    @Autowired
    MediaRepository mediaRepository;
}
