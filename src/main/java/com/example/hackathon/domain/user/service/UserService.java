
package com.example.hackathon.domain.user.service;

import com.example.hackathon.domain.user.dto.UserProfileDto;
import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.auth.dto.KakaoUserInfoDto;
import com.example.hackathon.global.exception.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findOrCreate(KakaoUserInfoDto userInfo) {
        return userRepository.findByKakaoId(userInfo.getId())
                .orElseGet(() -> userRepository.save(userInfo.toEntity()));
    }

    public UserProfileDto getProfile(Long id) {
        User user = userRepository.findByKakaoId(id)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new UserProfileDto(user);
    }
}


