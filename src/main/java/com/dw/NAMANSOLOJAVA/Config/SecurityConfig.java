package com.dw.NAMANSOLOJAVA.Config;

import com.dw.NAMANSOLOJAVA.Exception.MyAccessDeniedHandler;
import com.dw.NAMANSOLOJAVA.Exception.MyAuthenticationEntryPoint;
import com.dw.NAMANSOLOJAVA.Service.UserDetailService;
import com.dw.NAMANSOLOJAVA.jwt.JwtFilter;
import com.dw.NAMANSOLOJAVA.jwt.TokenProvider;
import io.github.cdimascio.dotenv.Dotenv;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
                                new AntPathRequestMatcher("/api/album/all"),
                                new AntPathRequestMatcher("/api/album/id/**"),
                                new AntPathRequestMatcher("/api/comment/id/**"),
                                new AntPathRequestMatcher("/api/comment/album-id/**"),
                                new AntPathRequestMatcher("/api/recomment/id/**"),
                                new AntPathRequestMatcher("/api/recomment/comment-id/**"),
                                new AntPathRequestMatcher("/api/recommend_place/all"),
                                new AntPathRequestMatcher("/api/recommend_place/single/**"),
                                new AntPathRequestMatcher("/api/recommend_place/region/**"),
                                new AntPathRequestMatcher("/api/user/download/**"),
                                new AntPathRequestMatcher("/api/album/download/**"),
                                new AntPathRequestMatcher("/api/recommend_place/download/**"),
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

    // 암호화와 복호화
    // BCrypt 는 복호화가 불가능하다.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static final Dotenv dotenv = Dotenv.configure()
            .filename(".env")
            .ignoreIfMissing()
            .load();

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 모든 도메인에서 오는 요청을 허용하도록 설정 (자세한 도메인 설정이 필요 없다면)
        configuration.setAllowedOrigins(Arrays.asList("*")); // '*'는 모든 도메인 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

