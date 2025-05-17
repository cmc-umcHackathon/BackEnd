package com.example.hackathon.global.auth.dto;

import com.example.hackathon.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Map;

import static com.example.hackathon.domain.user.enums.OAuthProvider.KAKAO;

@Getter
@NoArgsConstructor
@ToString
public class KakaoUserInfoDto {

    private Long id;
    private String name;

    @JsonCreator
    public KakaoUserInfoDto(
            @JsonProperty("id") Long id,
            @JsonProperty("kakao_account") Map<String, Object> account
    ) {
        this.id = id;

        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        this.name = profile != null ? (String) profile.get("nickname") : null;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .oauthProvider(KAKAO)
                .build();
    }
}

