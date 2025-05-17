package com.example.hackathon.domain.activity.service;


import com.example.hackathon.domain.activity.dto.ActivityRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
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
}
