package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Repository.AuthorityRepository;
import com.dw.NAMANSOLOJAVA.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public List<Authority> getAllAuthority(){
        return null;
    }

    public Authority getAuthorityById(String id){
      return null;
    }


}
