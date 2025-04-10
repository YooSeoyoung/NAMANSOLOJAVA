package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.*;
import com.dw.NAMANSOLOJAVA.Service.UserService;
import com.dw.NAMANSOLOJAVA.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register") // 회원가입
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                userService.registerUser(userDTO),
                HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return new ResponseEntity<>(userService.getCurrentUser().toUserDTO(), HttpStatus.OK);
    }
//    @GetMapping("/id/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable String username){
//        return new ResponseEntity<>(userService.getUserById(username),HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/check-id/{username}")
    public ResponseEntity<Boolean> checkId(@PathVariable String username){
        return new ResponseEntity<>(userService.checkId(username),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all/add-date")
    public ResponseEntity<List<UserAddDateDTO>> getAllUsersAddDate(){
        return new ResponseEntity<>(
                userService.getAllUsersAddDate(),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/id/{username}")
    public ResponseEntity<UserAddDateDTO> getUserByIdAdim(@PathVariable String username) {
        return new ResponseEntity<>(
                userService.getUserByIdAdmin(username),
                HttpStatus.OK);
    }

    @PostMapping("/find-user/email")
    public ResponseEntity<String> getIdByEmail(@RequestBody UserUpdateAndFIndDTO dto) {
        return new ResponseEntity<>(userService.getIdByEmail(dto), HttpStatus.OK);
    }

    @PostMapping("/find-user/phone")
    public ResponseEntity<String> getIdByPhone(@RequestBody UserUpdateAndFIndDTO userUpdateAndFIndDTO) {
        return new ResponseEntity<>(
                userService.getIdByPhone(userUpdateAndFIndDTO),
                HttpStatus.OK);
    }

    @PutMapping("/modify-pw")
    public ResponseEntity<String> UpdatePw(@RequestBody PasswordDTO passwordDTO) {
        return new ResponseEntity<>(
                userService.UpdatePw(passwordDTO),
                HttpStatus.OK);
    }

    
    @PutMapping("/user-data")
    public ResponseEntity<UserUpdateAndFIndDTO> UpdateUserData(@RequestBody UserUpdateAndFIndDTO userUpdateAndFIndDTO) {
        return new ResponseEntity<>(
                userService.UpdateUserData(userUpdateAndFIndDTO),
                HttpStatus.OK);
    }

    @PutMapping("/user-data-image-dday")
    public ResponseEntity<UpdateImageDDayDTO> UpdateUserDataImageDday(@RequestBody UpdateImageDDayDTO updateImageDDayDTO) {
        return new ResponseEntity<>(
                userService.UpdateUserDataImageDday(updateImageDDayDTO),
                HttpStatus.OK);
    }

    @PutMapping("/user/alarm-setting")
    public ResponseEntity<UserAlarmSettingDTO> AlarmSetting(@RequestBody UserAlarmSettingDTO userAlarmSettingDTO) {
        return new ResponseEntity<>(
                userService.AlarmSetting(userAlarmSettingDTO),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/monthly/album-count")
    public ResponseEntity<List<MonthlyUserAlbumCountDTO>> getMonthlyAlbumCountForAllUsers() {
        return ResponseEntity.ok(userService.monthlyAlbumCountForAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/last-activity") // 오타 수정
    public ResponseEntity<List<UserLastActivityDTO>> getUserLastActivity() {
        return new ResponseEntity<>(
                userService.getUserLastActivity(), // 파라미터 제거
                HttpStatus.OK);
    }

}
