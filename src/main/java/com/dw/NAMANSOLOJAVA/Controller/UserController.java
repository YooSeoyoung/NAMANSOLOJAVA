package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.PictureAndVideoDTO;
import com.dw.NAMANSOLOJAVA.DTO.UserAddDateDTO;
import com.dw.NAMANSOLOJAVA.DTO.UserDTO;
import com.dw.NAMANSOLOJAVA.Service.UserService;
import com.dw.NAMANSOLOJAVA.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(Media media) {
        return new ResponseEntity<>(userService.getCurrentUser().toUserDTO(media), HttpStatus.OK);
    }

    @GetMapping("/check-id/{username}")
    public ResponseEntity<Boolean> checkId(@PathVariable String username){
        return new ResponseEntity<>(userService.checkId(username),HttpStatus.OK);
    }

    @GetMapping("/all/add-date")
    public ResponseEntity<List<UserAddDateDTO>> getAllUsersAddDate(){
        return new ResponseEntity<>(
                userService.getAllUsersAddDate(),
                HttpStatus.OK);
    }
    @GetMapping("admin/id/{username}")
    public ResponseEntity<UserAddDateDTO> getUserByIdAdim(@PathVariable String username) {
        return new ResponseEntity<>(
                userService.getUserByIdAdmin(username),
                HttpStatus.OK);
    }
    @GetMapping("/find-user/email")
    public ResponseEntity<String> getIdByEmail(@RequestParam String email, @RequestParam String realName) {
        return new ResponseEntity<>(
                userService.getIdByEmail(email,realName),
                HttpStatus.OK);
    }
    @GetMapping("/find-user/phone")
    public ResponseEntity<String> getIdByPhone(@RequestParam String phone, @RequestParam String realName) {
        return new ResponseEntity<>(
                userService.getIdByPhone(phone,realName),
                HttpStatus.OK);
    }


}
