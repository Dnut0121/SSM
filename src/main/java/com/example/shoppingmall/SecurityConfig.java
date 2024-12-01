package com.example.shoppingmall;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 정적 리소스 접근 허용
                .requestMatchers("/login", "/signup", "/", "/product-list", "/product-list?category=*").permitAll() // 로그인과 회원가입 페이지는 모두 접근 가능 
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
            )
            .formLogin(form -> form
                .loginPage("/login") // 커스텀 로그인 페이지 경로
                .defaultSuccessUrl("/", true) // 로그인 성공 후 이동할 경로
                .failureUrl("/?error=true") // 로그인 실패 시 이동할 경로
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/index") // 로그아웃 성공 후 이동할 경로
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
                .clearAuthentication(true) // 인증 정보 초기화
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // 필요 시 CSRF 비활성화 (추천: API 서버인 경우)

        return http.build();
    }
}
