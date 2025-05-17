package com.example.hackathon.global.auth.service;

import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.auth.client.KakaoOAuthClient;
import com.example.hackathon.global.auth.dto.KakaoUserInfoDto;
import com.example.hackathon.global.auth.dto.TokenResponseDto;
import com.example.hackathon.global.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KakaoOAuthService implements OAuthService {

    private final KakaoOAuthClient kakaoOAuthClient;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public KakaoOAuthService(
            KakaoOAuthClient kakaoOAuthClient,
            UserRepository userRepository,
            JwtUtil jwtUtil
    ) {
        this.kakaoOAuthClient = kakaoOAuthClient;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String loginWithKakao(String code) {
        // 1. 카카오로부터 토큰 발급
        TokenResponseDto tokenDto = kakaoOAuthClient.getToken(code);

        // 2. 사용자 정보 조회
        KakaoUserInfoDto userInfo = kakaoOAuthClient.getUserInfo(tokenDto.getAccessToken());

        // 3. 회원 조회 또는 저장
        User user = userRepository.findByKakaoId(userInfo.getId())
                .orElseGet(() -> userRepository.save(userInfo.toEntity()));

        // 4. JWT 토큰 발급
        return jwtUtil.generateToken(user.getKakaoId());
    }
}

