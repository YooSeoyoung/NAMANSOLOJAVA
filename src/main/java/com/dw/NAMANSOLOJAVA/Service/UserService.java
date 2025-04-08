package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
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

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO registerUser(UserDTO userDTO){ // 회원가입
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
    public User getCurrentUser() { //현재 유저
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
    public List<UserAddDateDTO> getAllUsersAddDate() { // 관리자가 전체 유저/회원가입일 조회
        return null;
    }
    public UserAddDateDTO getUserByIdAdmin(String username) { //관리자가 username를 통한 유저/회원가입일 조회
     return null;
    }
//    public UserDTO getUserById(String username) { //id를 통한 유저 조회
//        return null;
//    }
    public String getIdByEmail(UserUpdateAndFIndDTO userUpdateAndFIndDTO) { // 이메일로 통하여 아이디 찾기
    return null;
    }
    public String getIdByPhone(UserUpdateAndFIndDTO userUpdateAndFIndDTO) { // 전화번호로 통하여 아이디 찾기
        return null;
    }
    public String UpdatePw(PasswordDTO passwordDTO) { // 전화번호로 통하여 아이디 찾기
        return null;
    }
    public UserUpdateAndFIndDTO UpdateUserData(UserUpdateAndFIndDTO userUpdateAndFIndDTO) { // 회원 정보 수정(이름, 이메일, 전화번호)
        return null;
    }
    public UpdateImageDDayDTO UpdateUserDataImageDday(UpdateImageDDayDTO updateImageDDayDTO) { // 회원 정보 수정(이름, 이메일, 전화번호)
        return null;
    }
    public UserAlarmSettingDTO AlarmSetting(UserAlarmSettingDTO userAlarmSettingDTO){
        return null;
    }
    public List<MonthlyUserAlbumCountDTO> monthlyUserAlbumCount(MonthlyUserAlbumCountDTO monthlyUserAlbumCountDTO){
        return null;
    }

}
