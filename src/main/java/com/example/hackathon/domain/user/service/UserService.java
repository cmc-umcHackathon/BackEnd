/*
package com.example.hackathon.user.service;

import com.example.hackathon.auth.dto.KakaoUserInfoDto;
import com.example.hackathon.common.exception.UsernameNotFoundException;
import com.example.hackathon.user.dto.UserProfileDto;
import com.example.hackathon.user.entity.User;
import com.example.hackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findOrCreate(KakaoUserInfoDto userInfo) {
        return userRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> userRepository.save(userInfo.toEntity()));
    }

    public UserProfileDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new UserProfileDto(user);
    }
}

*/
