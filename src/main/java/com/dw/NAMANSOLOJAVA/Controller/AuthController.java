package com.dw.NAMANSOLOJAVA.Controller;


import com.dw.NAMANSOLOJAVA.DTO.AlarmDTO;
import com.dw.NAMANSOLOJAVA.DTO.LoginDTO;
import com.dw.NAMANSOLOJAVA.DTO.TokenDTO;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.Service.UserService;
import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import com.dw.NAMANSOLOJAVA.jwt.JwtFilter;
import com.dw.NAMANSOLOJAVA.jwt.TokenProvider;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SimpMessagingTemplate messagingTemplate;

    // 생성자함수를 사용하면 @Autowired가 필요없음 (스프링이 권장하는 방법)
    public AuthController(TokenProvider tokenProvider,
                          AuthenticationManagerBuilder authenticationManagerBuilder,
                          SimpMessagingTemplate messagingTemplate) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginDTO loginDTO) {

        // 1. 유저정보와 DB상의 정보를 비교하기 위해 시큐리티인증객체 형태로 만듬
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                        loginDTO.getPassword());

        // 2. 인증정보와 DB에 저장되어있는 정보를 인증하는 코드
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);
        // 3. SecurityContextHolder에 인증이 성공한 유저정보를 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4. JWT 생성
        String jwt = tokenProvider.createToken(authentication);

        // 5. 응답헤더에 Bearer 형태로 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        String username = authentication.getName();
        String authority = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

//        AlarmDTO alarmDTO = AlarmDTO.builder()
//                .type(AlarmType.EVENT)
//                .message("관리자가 새로운 공지를 등록했습니다.")
//                .isRead(false)
//                .build();
//
//        messagingTemplate.convertAndSendToUser(username, "/queue/private", alarmDTO);

        return new ResponseEntity<>(
                new TokenDTO(jwt, username, authority),
                httpHeaders,
                HttpStatus.OK);
    }

}
