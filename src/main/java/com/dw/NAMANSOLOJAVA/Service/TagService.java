package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.AlbumDTO;
import com.dw.NAMANSOLOJAVA.Repository.TagRepository;
import com.dw.NAMANSOLOJAVA.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

//    public Tag getTagId(Long id){
//        return  null;
//    }
//
//    public Tag getTagName(String name){
//        return  null;
//    }
//
//    public Tag saveTag(Tag tag){
//        return  null;
//    }

    // 삭제는 불가능( 타 유저가 사용 가능함)
}
