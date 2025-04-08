package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.GreatDTO;
import com.dw.NAMANSOLOJAVA.Repository.GreatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreatService {
    @Autowired
    GreatRepository greatRepository;

    public GreatDTO saveGreat(GreatDTO greatDTO){
        return null;
    }
    public String deleteGreat(GreatDTO greatDTO){
        return null;
    }
}
