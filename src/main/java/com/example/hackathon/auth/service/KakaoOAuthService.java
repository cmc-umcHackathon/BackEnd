package com.example.hackathon.auth.service;

import com.example.hackathon.auth.client.KakaoOAuthClient;
import com.example.hackathon.auth.dto.KakaoUserInfoDto;
import com.example.hackathon.auth.dto.TokenResponseDto;
import com.example.hackathon.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KakaoOAuthService implements OAuthService {

    private final KakaoOAuthClient kakaoOAuthClient;
    private final JwtUtil jwtUtil;

    public KakaoOAuthService(KakaoOAuthClient kakaoOAuthClient, JwtUtil jwtUtil) {
        this.kakaoOAuthClient = kakaoOAuthClient;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String loginWithKakao(String code) {
        // 1. 카카오로부터 토큰 발급
        TokenResponseDto tokenDto = kakaoOAuthClient.getToken(code);

        log.info("tokenDto : {}", tokenDto);
        // 2. 사용자 정보 조회
        KakaoUserInfoDto userInfo = kakaoOAuthClient.getUserInfo(tokenDto.getAccessToken());
        log.info("userInfo : {}", userInfo);

        // 3. 회원 조회 또는 저장
//        User user = userRepository.findByEmail(userInfo.getEmail())
//                .orElseGet(() -> userRepository.save(userInfo.toEntity()));

        // 4. JWT 토큰 발급
//        return jwtUtil.generateToken(user.getEmail());
        log.info("JWT TOKEN : {}", jwtUtil.generateToken(userInfo.getId()));
        return "";
    }
}

