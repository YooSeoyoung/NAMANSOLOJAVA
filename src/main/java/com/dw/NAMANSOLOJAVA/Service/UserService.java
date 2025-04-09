package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Exception.UnauthorizedUserException;
import com.dw.NAMANSOLOJAVA.Repository.AuthorityRepository;
import com.dw.NAMANSOLOJAVA.Repository.MediaRepository;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.model.Authority;
import com.dw.NAMANSOLOJAVA.model.Media;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    MediaRepository mediaRepository;

    public UserDTO registerUser(UserDTO userDTO){ // 회원가입
        Optional<User> existingUser = userRepository.findById(userDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new InvalidRequestException("이미 존재하는 사용자명입니다.");
        }

        // 권한 조회
        Authority authority = authorityRepository.findById(userDTO.getAuthority())
                .orElseThrow(() -> new RuntimeException("권한이 존재하지 않습니다: " + userDTO.getAuthority()));

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        newUser.setRealNameM(userDTO.getRealNameM());
        newUser.setRealNameF(userDTO.getRealNameF());
        newUser.setEmailM(userDTO.getEmailM());
        newUser.setEmailF(userDTO.getEmailF());
        newUser.setPhoneNumberM(userDTO.getPhoneNumberM());
        newUser.setPhoneNumberF(userDTO.getPhoneNumberF());
        newUser.setBirthM(userDTO.getBirthM());
        newUser.setBirthF(userDTO.getBirthF());
        newUser.setDDay(userDTO.getDDay());
        newUser.setAddDate(LocalDate.now());
        newUser.setAuthority(authority);

        // 기본 알림 설정
        newUser.setAlarmAlert(true);
        newUser.setCommentAlert(true);
        newUser.setFollowAlert(true);
        newUser.setGreatAlert(true);
        newUser.setEventAlert(true);
        newUser.setRecommendAlert(true);
        newUser.setRecommentAlert(true);
        newUser.setTodoAlert(true);

        Media defaultMedia = mediaRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("기본 이미지가 존재하지 않습니다."));
        newUser.setMedia(defaultMedia);

        userRepository.save(newUser);

        return newUser.toUserDTO();
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

    public List<UserAddDateDTO> getAllUsersAddDate() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserAddDateDTO(user.getUsername(), user.getAddDate()))
                .toList();
    }
    public UserAddDateDTO getUserByIdAdmin(String username) { //관리자가 username를 통한 유저/회원가입일 조회
     return null;
    }
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
    public List<MonthlyUserAlbumCountDTO> monthlyUserAlbumCount(MonthlyUserAlbumCountDTO monthlyUserAlbumCountDTO){return null;}
    public List<UserLastActivityDTO> getUserLastActivity(UserLastActivityDTO userLastActivityDTO){return null;}

}
