package com.example.hackathon.domain.user.dto;

import com.example.hackathon.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileDto {

    private Long kakaoId;
    private String nickname;
    private String role;

    public UserProfileDto(User user) {
        this.kakaoId = user.getKakaoId();
        this.nickname = user.getName();
    }
}

