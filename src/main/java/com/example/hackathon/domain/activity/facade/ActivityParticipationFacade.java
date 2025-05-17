package com.example.hackathon.domain.activity.facade;

import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.service.ActivityService;
import com.example.hackathon.domain.activityhistory.service.ActivityHistoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityParticipationFacade {

    private final ActivityService activityService;
    private final ActivityHistoryService historyService;

    public ActivityParticipationFacade(ActivityService activityService, ActivityHistoryService historyService) {
        this.activityService = activityService;
        this.historyService = historyService;
    }

    public List<ActivityResponseDto> getAvailableActivities(
            String userId,
            Long categoryId
    ) {
        // 1. 오늘의 활동 이력 ID 목록 조회
        List<Long> doneActivityIds = historyService.getTodayActivityIdsByUser(userId);

        // 2. 활동 전체 중 오늘 이미 한 활동 이력 체크
        return activityService.getActivitiesByCategoryWithTodayFlags(categoryId, doneActivityIds);
    }

}
