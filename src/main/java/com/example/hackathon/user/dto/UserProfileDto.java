package com.example.hackathon.user.dto;

import com.example.hackathon.user.entity.User;
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
        this.nickname = user.getNickname();
        this.role = user.getRole();
    }
}

