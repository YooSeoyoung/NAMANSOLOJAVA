package com.dw.NAMANSOLOJAVA.Config;

import com.dw.NAMANSOLOJAVA.Exception.MyAccessDeniedHandler;
import com.dw.NAMANSOLOJAVA.Exception.MyAuthenticationEntryPoint;
import com.dw.NAMANSOLOJAVA.Service.UserDetailService;
import com.dw.NAMANSOLOJAVA.jwt.JwtFilter;
import com.dw.NAMANSOLOJAVA.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests(auth -> auth
                        .requestMatchers(
                                // 허용 범위( 즉, jwt 토큰이 없어도 보안을 거치지 말고 모두 허락 , restapi랑 html *은 자식 , **은 자식의 후손까지 전부)
                                new AntPathRequestMatcher("/*.html"),
                                new AntPathRequestMatcher("/api/authenticate"),
                                new AntPathRequestMatcher("/api/products/**"),
                                new AntPathRequestMatcher("/api/album/**"),
                                new AntPathRequestMatcher("/api/user/**"),
                                new AntPathRequestMatcher("/api/album_tag/**"),
                                new AntPathRequestMatcher("/api/authority/**"),
                                new AntPathRequestMatcher("/api/category/**"),
                                new AntPathRequestMatcher("/api/category_place/**"),
                                new AntPathRequestMatcher("/api/comment/**"),
                                new AntPathRequestMatcher("/api/recomment/**"),
                                new AntPathRequestMatcher("/api/event_present/**"),
                                new AntPathRequestMatcher("/api/follow/**"),
                                new AntPathRequestMatcher("/api/great/**"),
                                new AntPathRequestMatcher("/api/media/**"),
                                new AntPathRequestMatcher("/api/official_event/**"),
                                new AntPathRequestMatcher("/api/recommend_place/**"),
                                new AntPathRequestMatcher("/api/tag/**"),
                                new AntPathRequestMatcher("/api/todo/**"),
                                new AntPathRequestMatcher("/ws/**"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/img/**"),
                                new AntPathRequestMatcher("/mp4/**"),
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/v3/api-docs/**")
                        ).permitAll()
                        //uploads 밑에 있는 파일은 누가됬든 토큰이 있어도 보안을 거치지 않고 무조건 다 막기!!
                        .requestMatchers("/uploads/**").denyAll()
                        //유효한 토큰이면 접근을 허용하겠다
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        //세선 사용 시 (SessionCreationPolicy.ALWAYS) , STATELESS : 세션을 사용하지 않겠다
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //csrf : 기본으로 제공하는 로그인 창을 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                //사용자가 만든 필터를 추가하라는 의미(JwtFilter라는 우리가 만든 필터를 추가)
                .exceptionHandling(exception->exception.authenticationEntryPoint(new MyAuthenticationEntryPoint()).
                        accessDeniedHandler(new MyAccessDeniedHandler()))
                .addFilterBefore(
                        new JwtFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // 암호화와 복호화
    // BCrypt 는 복호화가 불가능하다.
}
