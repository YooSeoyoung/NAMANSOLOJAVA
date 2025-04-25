package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Exception.InvalidRequestException;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Exception.UnauthorizedUserException;
import com.dw.NAMANSOLOJAVA.Repository.*;
import com.dw.NAMANSOLOJAVA.enums.MediaType;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.Authority;
import com.dw.NAMANSOLOJAVA.model.Media;
import com.dw.NAMANSOLOJAVA.model.User;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private JavaMailSender mailSender;

    public UserDTO registerUser(UserDTO userDTO){ // 회원가입
        Optional<User> existingUser = userRepository.findById(userDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new InvalidRequestException("이미 존재하는 사용자명입니다.");
        }

        // 권한 조회
//        Authority authority = authorityRepository.findById(userDTO.getAuthority())
//                .orElseThrow(() -> new RuntimeException("권한이 존재하지 않습니다: " + userDTO.getAuthority()));

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
        System.out.println(userDTO.getDDay());
        newUser.setDDay(userDTO.getDDay());
        newUser.setAddDate(LocalDate.now());
        newUser.setAuthority(authorityRepository.findById("ROLE_USER")
                .orElseThrow(()->new ResourceNotFoundException("No role")));
        newUser.setCity(userDTO.getCity());

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
                .orElseThrow(() -> new ResourceNotFoundException("기본 이미지가 존재하지 않습니다."));
        newUser.setMedia(defaultMedia);

        System.out.println("DDay: " + newUser.getDDay());

        userRepository.save(newUser);

        officialEventService.applyOfficialEventsToUser(newUser);

        sendWelcomeEmail(newUser.getEmailF(), newUser.getRealNameF());
        sendWelcomeEmail(newUser.getEmailM(), newUser.getRealNameM());

        return newUser.toUserDTO();
    }

    public void sendWelcomeEmail(String toEmail, String realName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setFrom("ojungjae@gmail.com"); // Gmail에서는 이 주소여야 함
            helper.setSubject("🎉 회원가입을 축하드립니다!");
            helper.setText("<h1>" + realName + "님 환영합니다!</h1><p>감사합니다 😊</p>", true);

            mailSender.send(message);
            System.out.println("✅ 이메일 전송 완료!");
        } catch (Exception e) {
            System.err.println("❌ 이메일 전송 실패: " + e.getMessage());
        }
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
    public  boolean checkEmail(String email){return  userRepository.existsByEmailMOrEmailF(email);}
    public  boolean checkPhone(String phone){return  userRepository.existsByPhoneNumberMOrPhoneNumberF(phone);}

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

    public String getIdByPhone(String realName, String phoneNumber){
        Optional<User> femaleUser = userRepository.findByRealNameFAndPhoneNumberF(realName, phoneNumber);
        if (femaleUser.isPresent()) {
            return femaleUser.get().getUsername();
        }

        Optional<User> maleUser = userRepository.findByRealNameMAndPhoneNumberM(realName, phoneNumber);
        if (maleUser.isPresent()) {
            return maleUser.get().getUsername();
        }

        throw new ResourceNotFoundException("입력하신 정보와 일치하는 아이디가 없습니다.");
    }

    public String getIdByEmail(String realName, String email){
        Optional<User> femaleUser = userRepository.findByRealNameFAndEmailF(realName, email);
        if (femaleUser.isPresent()) {
            return femaleUser.get().getUsername();
        }

        Optional<User> maleUser = userRepository.findByRealNameMAndEmailM(realName, email);
        if (maleUser.isPresent()) {
            return maleUser.get().getUsername();
        }

        throw new ResourceNotFoundException("입력하신 정보와 일치하는 아이디가 없습니다.");
    }

    public Boolean ExistEmailAndUsername(String username,String realName, String email){
        Optional<User> femaleUser = userRepository.findByUsernameAndRealNameFAndEmailF(username,realName, email);
        if (femaleUser.isPresent()) {
            return true;
        }
        Optional<User> maleUser = userRepository.findByUsernameAndRealNameMAndEmailM(username,realName, email);
        if (maleUser.isPresent()) {
            return false;
        }
        throw new ResourceNotFoundException("입력하신 정보가 틀립니다");
    }

    @Transactional
    public String UpdatePw(PasswordDTO passwordDTO) {
        // 1. 유저 존재 여부 체크
        User user = userRepository.findById(passwordDTO.getUsername())
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

    @Transactional
    public UserUpdateAndFIndDTO UpdateUserData(UserUpdateAndFIndDTO userUpdateAndFIndDTO) { // 회원 정보 수정(이름, 이메일, 전화번호)
        User currentUser = getCurrentUser();

        if (userUpdateAndFIndDTO.getRealNameM() != null) {
            currentUser.setRealNameM(userUpdateAndFIndDTO.getRealNameM());
        }
        
        if (userUpdateAndFIndDTO.getRealNameF() != null) {
            currentUser.setRealNameF(userUpdateAndFIndDTO.getRealNameF());
        }

        if (userUpdateAndFIndDTO.getEmailM() != null) {
            currentUser.setEmailM(userUpdateAndFIndDTO.getEmailM());
        }

        if (userUpdateAndFIndDTO.getEmailF() != null) {
            currentUser.setEmailF(userUpdateAndFIndDTO.getEmailF());
        }

        if (userUpdateAndFIndDTO.getPhoneNumberM() != null) {
            currentUser.setPhoneNumberM(userUpdateAndFIndDTO.getPhoneNumberM());
        }

        if (userUpdateAndFIndDTO.getPhoneNumberF() != null) {
            currentUser.setPhoneNumberF(userUpdateAndFIndDTO.getPhoneNumberF());
        }

        if (userUpdateAndFIndDTO.getDDay() != null) {
            currentUser.setDDay(userUpdateAndFIndDTO.getDDay());

            officialEventService.refreshOfficialEvents(currentUser);
        }

        if (userUpdateAndFIndDTO.getCity() != null) {
            currentUser.setCity(userUpdateAndFIndDTO.getCity());
        }

        if (userUpdateAndFIndDTO.getProfileImageUrl() != null) {
            Media media = new Media();
            media.setMediaUrl(userUpdateAndFIndDTO.getProfileImageUrl()); // ex: /api/user/download/username/username.jpg
            media.setMediaType(MediaType.valueOf("PICTURE"));
            Media savedMedia = mediaRepository.save(media);
            currentUser.setMedia(savedMedia);
        }
        userRepository.save(currentUser);

        return new UserUpdateAndFIndDTO(
                currentUser.getRealNameM(),
                currentUser.getRealNameF(),
                currentUser.getEmailM(),
                currentUser.getEmailF(),
                currentUser.getPhoneNumberM(),
                currentUser.getPhoneNumberF(),
                currentUser.getMedia().getMediaUrl(),
                currentUser.getDDay(),
                currentUser.getCity()
        );
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

    public void updateCity(String city) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        user.setCity(city); // 도시 변경
        userRepository.save(user); // DB에 반영
    }
}
