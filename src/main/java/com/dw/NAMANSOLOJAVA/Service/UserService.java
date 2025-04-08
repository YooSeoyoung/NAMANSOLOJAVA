package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.UserDTO;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO registerUser(UserDTO userDTO){
        return null;
    }

    public boolean validateUser(String username, String password) {
//        User user = userRepository.findById(username)
//                .orElseThrow(()->new ResourceNotFoundException("Invalid Username"));
//        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }


    public User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            throw new UnauthorizedUserException("User is not authenticated");
//        }
//        return userRepository.findById(authentication.getName())
//                .orElseThrow(()->new ResourceNotFoundException("No username"));
    }
}
