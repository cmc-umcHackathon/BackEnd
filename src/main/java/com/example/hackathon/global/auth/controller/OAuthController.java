package com.example.hackathon.global.auth.controller;

import com.example.hackathon.global.auth.service.KakaoOAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class OAuthController {

    private final KakaoOAuthService kakaoOAuthService;

    public OAuthController(KakaoOAuthService kakaoOAuthService) {
        this.kakaoOAuthService = kakaoOAuthService;
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoLogin(@RequestParam("code") String code) {
        String jwtToken = kakaoOAuthService.loginWithKakao(code);
        return ResponseEntity.ok().body(jwtToken);
    }
}

