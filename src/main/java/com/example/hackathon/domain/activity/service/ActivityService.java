package com.example.hackathon.domain.activity.service;


import com.example.hackathon.domain.activity.dto.ActivityNewRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.entity.Activity;
import com.example.hackathon.domain.activity.repository.ActivityRepository;
import com.example.hackathon.domain.category.entity.Category;
import com.example.hackathon.domain.category.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ActivityResponseDto> getActivitiesByCategoryWithTodayFlags(Long categoryId, List<Long> todayActivityIds) {
        return activityRepository.findByCategoryIdAndIsDisplayedTrueOrderBySortOrderAsc(categoryId).stream()
                .map(activity -> toResponseDto(activity, todayActivityIds.contains(activity.getId())))
                .collect(Collectors.toList());
    }

    public List<ActivityResponseDto> getTodayMissionActivitiesWithTodayFlags(List<Long> todayActivityIds) {
        return activityRepository.findByIsDisplayedTrueAndIsTodayActivityTrueOrderBySortOrderAsc().stream()
                .map(activity -> toResponseDto(activity, todayActivityIds.contains(activity.getId())))
                .collect(Collectors.toList());
    }

    public ActivityResponseDto getActivity(Long activityId) {
        return activityRepository.findById(activityId)
                .map(this::toResponseDto)
                .orElse(null);
    }

    public void addUserActivities(Long userId, ActivityNewRequestDto.AddActivity request) {
        User user = userRepository.findByKakaoId(userId)
                .orElseThrow(() -> new BusinessException(Code.USER_NOT_FOUND, "이미 오늘 참여한 활동입니다."));

        Category category = categoryRepository.findByCategoryType(request.getCategoryType())
                .orElseThrow(() -> new BusinessException(Code.CATEGORY_NOT_FOUND));

        Activity activity = Activity.builder()
                .user(user)
                .title(request.getTitle())
                .category(category)
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
                activity.getIsCustom()
        );
    }

    private ActivityResponseDto toResponseDto(Activity activity, boolean isTodayActivity) {
        return new ActivityResponseDto(
                activity.getId(),
                activity.getDescription(),
                activity.getPoint(),
                activity.getSortOrder(),
                isTodayActivity,
                activity.getIsCustom()
        );
    }

}
