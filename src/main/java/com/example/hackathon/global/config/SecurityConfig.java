package com.example.hackathon.global.config;

import com.example.hackathon.global.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ✅ 기본 폼 로그인 화면 제거
                .formLogin(AbstractHttpConfigurer::disable)

                // ✅ CSRF 비활성화 (API 서버일 경우 필요)
                .csrf(AbstractHttpConfigurer::disable)

                // ✅ 세션 사용 안 함 (JWT 기반)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // ✅ 경로별 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",      // 소셜 로그인 콜백
                                "/login",        // 혹시 사용할 경우
                                "/health",       // 헬스 체크
                                "/docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // ✅ 기타 설정 (필요 시 추가)
                .httpBasic(Customizer.withDefaults());

        // ✅ JWT 인증 필터 적용
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

