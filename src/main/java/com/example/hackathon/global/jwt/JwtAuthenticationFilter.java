package com.example.hackathon.global.jwt;

import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.List;


import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    private static final List<String> EXCLUDED_URLS = List.of(
            "auth/kakao/callback","/api/auth/login/kakao", "/swagger-ui", "/v3/api-docs", "/"
    );


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {


        String path = request.getRequestURI();
        if (isExcluded(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);

        try {
            if (token != null && jwtUtil.validateToken(token)) {
                String id = jwtUtil.extractKakaoId(token);

                User user = userRepository.findByKakaoId(Long.valueOf(id))
                        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + id));

                // 인증 객체 생성 (권한 정보는 생략하거나 필요시 추가)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                null
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtException | IllegalArgumentException e) {
            logger.warn("JWT 인증 실패: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    private boolean isExcluded(String path) {
        return EXCLUDED_URLS.stream().anyMatch(path::startsWith);
    }
}

