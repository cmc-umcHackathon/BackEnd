package com.example.hackathon.domain.activity.service;


import com.example.hackathon.domain.activity.dto.ActivityNewRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.entity.Activity;
import com.example.hackathon.domain.activity.repository.ActivityRepository;
import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.exception.BusinessException;
import com.example.hackathon.global.response.Code;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public List<ActivityResponseDto> getActivitiesByCategoryExcludingIds(Long categoryId, List<Long> excludedIds) {
        return activityRepository.findByCategoryIdAndIsDisplayedTrueOrderBySortOrderAsc(categoryId).stream()
                .filter(a -> !excludedIds.contains(a.getId()))
                .map(this::toResponseDto)
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


    private ActivityResponseDto toResponseDto(Activity activity) {
        return new ActivityResponseDto(
                activity.getId(),
                activity.getDescription(),
                activity.getPoint(),
                activity.getSortOrder(),
                activity.getIsTodayActivity(),
                activity.getIsCustom()
        );
    }

}
