package com.example.hackathon.global.auth.controller;

import com.example.hackathon.global.auth.service.KakaoOAuthService;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "카카오 로그인 콜백 API",
            description = "카카오 OAuth 인증 후 리디렉션되는 콜백 엔드포인트입니다. 인가 코드를 통해 JWT 토큰을 반환합니다."
    )
    @GetMapping("/kakao/callback")
    public Response<?> kakaoLogin(@RequestParam("code") String code) {
        String jwtToken = kakaoOAuthService.loginWithKakao(code);
        return Response.ok(jwtToken);
    }
}

