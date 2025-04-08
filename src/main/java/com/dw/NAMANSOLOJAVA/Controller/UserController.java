package com.dw.NAMANSOLOJAVA.Controller;

import com.dw.NAMANSOLOJAVA.DTO.PictureAndVideoDTO;
import com.dw.NAMANSOLOJAVA.DTO.UserDTO;
import com.dw.NAMANSOLOJAVA.Service.UserService;
import com.dw.NAMANSOLOJAVA.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // JWT에서는 일반적으로 클라이언트가 현재 유저정보를 확인할 필요는 없음. 하지만,
    // 추가적인 사용자 정보나 최신 권한 상태를 확인해야 할 때, 또는 토큰 자체에 담지 않은 정보를 제공할 필요가 있을 때 사용할 수 있음
    // 조금 애매함
    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(Media media) {
        return new ResponseEntity<>(userService.getCurrentUser().toUserDTO(media), HttpStatus.OK);
    }

    @GetMapping("/check-id/{userName}")
    public ResponseEntity<Boolean> checkId(@PathVariable String userName){
        return new ResponseEntity<>(userService.checkId(userName),HttpStatus.OK);
    }


}
