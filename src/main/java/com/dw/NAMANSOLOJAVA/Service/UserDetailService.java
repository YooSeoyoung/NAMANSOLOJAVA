package com.dw.NAMANSOLOJAVA.Service;


import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if(user.isEmpty()){
            throw  new IllegalArgumentException(username);
        }
        User user1 = user.get();
        user1.setLastLogin(LocalDate.now());
        userRepository.save(user1);
        return user1;
    }
}
