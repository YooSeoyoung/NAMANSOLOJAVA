package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.UserDTO;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Exception.UnauthorizedUserException;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO registerUser(UserDTO userDTO){
        Optional<User> user = userRepository.findById(userDTO.getUsername());
        if (user.isPresent()) {
            throw new InvalidRequestException("Username already exists");
        }
//        return userRepository.save(
//                new User(
//                        userDTO.getUsername(),
//                        bCryptPasswordEncoder.encode(userDTO.getPassword()),
//                        userDTO.getEmail(),
//                        userDTO.getRealName(),
//                        authorityRepository.findById("ROLE_USER")
//                                .orElseThrow(()->new ResourceNotFoundException("No role")),
//                        LocalDateTime.now())
//        ).toDto();
        return null;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedUserException("User is not authenticated");
        }
        return userRepository.findById(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("No username"));
    }

    public boolean checkId(String username){
        return userRepository.existsById(username);
    }
}
