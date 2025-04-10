package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.Service.AuthorityService;
import com.dw.NAMANSOLOJAVA.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authority")
public class AuthorityController {
    @Autowired
    AuthorityService authorityService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Authority>> getAllAuthoritys() {
        return new ResponseEntity<>(
                authorityService.getAllAuthority(),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{id}")
    public ResponseEntity<Authority> getAuthorityById(@PathVariable String id){
        return new ResponseEntity<>(
                authorityService.getAuthorityById(id),HttpStatus.OK
        );
    }


}
