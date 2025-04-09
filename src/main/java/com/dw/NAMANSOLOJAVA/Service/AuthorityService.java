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

    public List<Authority> getAllAuthority() {
        return authorityRepository.findAll();
    }

    public Authority getAuthorityById(String id) {
        return authorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID에 해당하는 권한이 없습니다: " + id));
        }

    public Authority getAuthorityByName(String authorityName) {
        return authorityRepository.findByAuthorityName(authorityName)
                .orElseThrow(() -> new RuntimeException("권한을 찾을 수 없습니다: " + authorityName));
    }
}
