package com.example.hackathon.domain.activity.service;


import com.example.hackathon.domain.activity.dto.ActivityNewRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.entity.Activity;
import com.example.hackathon.domain.activity.repository.ActivityRepository;
import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.exception.BusinessException;
import com.example.hackathon.global.response.Code;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.hackathon.domain.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public List<ActivityResponseDto> getValidActivitiesByCategory(ActivityRequestDto requestDto) {
        return activityRepository.findByCategoryIdAndIsDisplayedTrueOrderBySortOrderAsc(requestDto.getCategoryId())
                .stream()
                .map(a -> new ActivityResponseDto(
                        a.getId(),
                        a.getDescription(),
                        a.getPoint(),
                        a.getSortOrder(),
                        a.getIsTodayActivity(),
                        a.getIsCustom()
                ))
                .collect(Collectors.toList());
    }

    public void addUserActivities(Long userId, ActivityNewRequestDto.AddActivity request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(Code.USER_NOT_FOUND));

        Activity activity = Activity.builder()
                .user(user)
                .title(request.getTitle())
                .category(request.getCategory())
                .description(request.getDescription())
                .repeatCycle(request.getRepeatCycle())
                .build();

        activityRepository.save(activity);
    }

}
