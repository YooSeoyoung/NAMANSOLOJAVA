package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Exception.UnauthorizedUserException;
import com.dw.NAMANSOLOJAVA.Repository.*;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.Authority;
import com.dw.NAMANSOLOJAVA.model.Media;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


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
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    OfficialEventService officialEventService;

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

        officialEventService.applyOfficialEventsToUser(newUser);

        return newUser.toUserDTO();
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::toUserDTO)
                .toList();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(" 인증 객체: " + authentication);

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            throw new UnauthorizedUserException("User is not authenticated");
        }

        String username = authentication.getName();
        System.out.println(" 인증된 사용자명: " + username);

        // DB에서 유저 조회
        return userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("No username: " + username));
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
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("해당 유저를 찾을 수 없습니다: " + username);
        }

        User user = userOptional.get();

        UserAddDateDTO dto = new UserAddDateDTO();
        dto.setUsername(user.getUsername());
        dto.setAddDate(user.getAddDate());

        return dto;
    }
    // 이메일로 통하여 아이디 찾기
    public String getIdByEmail(UserUpdateAndFIndDTO dto) {
        Optional<User> userOpt = userRepository.findByEmailMAndEmailFAndPhoneNumberMAndPhoneNumberFAndRealNameMAndRealNameF(
                dto.getEmailM(), dto.getEmailF(),
                dto.getPhoneNumberM(), dto.getPhoneNumberF(),
                dto.getRealNameM(), dto.getRealNameF());

        return userOpt
                .map(User::getUsername)
                .orElseThrow(() -> new RuntimeException("일치하는 사용자를 찾을 수 없습니다."));
    }

    public String getIdByPhone(UserUpdateAndFIndDTO userUpdateAndFIndDTO) { // 전화번호로 통하여 아이디 찾기
        Optional<User> foundUser = userRepository
                .findByPhoneNumberMAndRealNameMOrPhoneNumberFAndRealNameF(
                        userUpdateAndFIndDTO.getPhoneNumberM(), userUpdateAndFIndDTO.getRealNameM(),
                        userUpdateAndFIndDTO.getPhoneNumberF(), userUpdateAndFIndDTO.getRealNameF()
                );

        return foundUser
                .map(User::getUsername)
                .orElseThrow(() -> new UsernameNotFoundException("일치하는 회원 정보를 찾을 수 없습니다."));
    }

    @Transactional
    public String UpdatePw(PasswordDTO passwordDTO) {
        // 1. 유저 존재 여부 체크
        User user = userRepository.findById(passwordDTO.getUsername())
                .filter(u -> u.getEmailM().equals(passwordDTO.getEmail()) || u.getEmailF().equals(passwordDTO.getEmail()))
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없거나 이메일이 일치하지 않습니다."));

        // 2. 비밀번호 일치 확인
        if (!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmNewPassword())) {
            throw new RuntimeException("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        // 3. 비밀번호 암호화 후 저장
        String encodedPassword = bCryptPasswordEncoder.encode(passwordDTO.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return "비밀번호가 성공적으로 변경되었습니다.";
    }

    public UserUpdateAndFIndDTO UpdateUserData(UserUpdateAndFIndDTO userUpdateAndFIndDTO) { // 회원 정보 수정(이름, 이메일, 전화번호)
        User currentUser = getCurrentUser();

        if (userUpdateAndFIndDTO.getRealNameM() != null) currentUser.setRealNameM(userUpdateAndFIndDTO.getRealNameM());
        if (userUpdateAndFIndDTO.getRealNameF() != null) currentUser.setRealNameF(userUpdateAndFIndDTO.getRealNameF());
        if (userUpdateAndFIndDTO.getEmailM() != null) currentUser.setEmailM(userUpdateAndFIndDTO.getEmailM());
        if (userUpdateAndFIndDTO.getEmailF() != null) currentUser.setEmailF(userUpdateAndFIndDTO.getEmailF());
        if (userUpdateAndFIndDTO.getPhoneNumberM() != null) currentUser.setPhoneNumberM(userUpdateAndFIndDTO.getPhoneNumberM());
        if (userUpdateAndFIndDTO.getPhoneNumberF() != null) currentUser.setPhoneNumberF(userUpdateAndFIndDTO.getPhoneNumberF());

        userRepository.save(currentUser);

        UserUpdateAndFIndDTO updatedDTO = new UserUpdateAndFIndDTO(
                currentUser.getRealNameM(),
                currentUser.getRealNameF(),
                currentUser.getEmailM(),
                currentUser.getEmailF(),
                currentUser.getPhoneNumberM(),
                currentUser.getPhoneNumberF()
        );
        return updatedDTO;
    }

    public UpdateImageDDayDTO UpdateUserDataImageDday(UpdateImageDDayDTO updateImageDDayDTO) { // 회원 정보 수정(이름, 이메일, 전화번호)
        User currentUser = getCurrentUser();

        if (updateImageDDayDTO.getDDay() != null) {
            currentUser.setDDay(updateImageDDayDTO.getDDay());
        }
        if (updateImageDDayDTO.getMediaUrl() != null && updateImageDDayDTO.getMediaUrl().getId() != null) {
            Media media = mediaRepository.findById(updateImageDDayDTO.getMediaUrl().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("해당 ID의 미디어가 존재하지 않습니다."));
            currentUser.setMedia(media);
        }

        userRepository.save(currentUser);

        return currentUser.toUpdateImageDDayDTO();
    }

    public UserAlarmSettingDTO AlarmSetting(UserAlarmSettingDTO userAlarmSettingDTO){
        User user = getCurrentUser();

        user.setAlarmAlert(userAlarmSettingDTO.getAlarmAlert());
        user.setCommentAlert(userAlarmSettingDTO.getCommentAlert());
        user.setFollowAlert(userAlarmSettingDTO.getFollowAlert());
        user.setGreatAlert(userAlarmSettingDTO.getGreatAlert());
        user.setEventAlert(userAlarmSettingDTO.getEventAlert());
        user.setRecommendAlert(userAlarmSettingDTO.getRecommendAlert());
        user.setRecommentAlert(userAlarmSettingDTO.getRecommentAlert());
        user.setTodoAlert(userAlarmSettingDTO.getTodoAlert());

        userRepository.save(user);

        return userAlarmSettingDTO;
    }

    public List<MonthlyUserAlbumCountDTO> monthlyAlbumCountForAllUsers(){
        List<User> allUsers = userRepository.findAll();
        List<MonthlyUserAlbumCountDTO> result = new ArrayList<>();

        for (User user : allUsers) {
            List<Album> albums = albumRepository.findByUser_Username(user.getUsername());

            Map<Integer, Long> monthlyCount = albums.stream()
                    .collect(Collectors.groupingBy(
                            album -> album.getAddDate().getMonthValue(),
                            Collectors.counting()
                    ));

            for (int month = 1; month <= 12; month++) {
                long count = monthlyCount.getOrDefault(month, 0L);
                result.add(new MonthlyUserAlbumCountDTO(month, user.getUsername(), count));
            }
        }

        return result;
    }

    public List<UserLastActivityDTO> getUserLastActivity(){
        List<User> users = userRepository.findAll();
        List<UserLastActivityDTO> result = new ArrayList<>();
        List<LocalDate> loginDates = new ArrayList<>();

        for (User user : users) {
            LocalDate login = user.getLastLogin();
            if (login != null) {
                result.add(new UserLastActivityDTO(user.getUsername(), login));
                loginDates.add(login);
            }
        }

        // 평균 날짜 계산
        if (!loginDates.isEmpty()) {
            long sumEpochDays = loginDates.stream()
                    .mapToLong(LocalDate::toEpochDay)
                    .sum();

            long avgEpochDay = sumEpochDays / loginDates.size();
            LocalDate avgDate = LocalDate.ofEpochDay(avgEpochDay);

            result.add(new UserLastActivityDTO("AVG", avgDate));
        }

        return result;
    }


}
