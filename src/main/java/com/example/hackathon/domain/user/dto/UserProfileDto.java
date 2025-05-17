package com.example.hackathon.domain.user.dto;

import com.example.hackathon.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    private Long id;
    private String nickname;
    private String role;

    public UserProfileDto(User user) {
        this.id = user.getId();
        this.nickname = user.getName();
    }
}

