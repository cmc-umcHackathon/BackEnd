package com.example.hackathon.domain.activityhistory.facade;

import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.service.ActivityService;
import com.example.hackathon.domain.activityhistory.dto.ActivityHistoryRequestDto;
import com.example.hackathon.domain.activityhistory.dto.ActivityHistoryResponseDto;
import com.example.hackathon.domain.activityhistory.service.ActivityHistoryService;
import com.example.hackathon.domain.point.service.PointService;
import com.example.hackathon.global.exception.BusinessException;
import com.example.hackathon.global.response.Code;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ActivityPointGrantFacade {

    private final ActivityHistoryService activityHistoryService;
    private final ActivityService activityService;
    private final PointService pointService;

    public ActivityPointGrantFacade(
            ActivityHistoryService activityHistoryService,
            ActivityService activityService,
            PointService pointService
    ) {
        this.activityHistoryService = activityHistoryService;
        this.activityService = activityService;
        this.pointService = pointService;
    }

    @Transactional
    public void grantParticipationAndPoint(
            Long userId,
            ActivityHistoryRequestDto requestDto
    ) {
        ActivityResponseDto activity = activityService.getActivity(requestDto.getCategoryId());
        if (activity == null) {
            throw new BusinessException(Code.BAD_REQUEST, "유효하지 않은 활동 ID 입니다.");
        }

        ActivityHistoryResponseDto activityHistoryResponseDto =
                activityHistoryService.getTodayAttendedHistory(
                        userId,
                        activity.getId()
                );

        if (isAlreadyAttendedToday(activityHistoryResponseDto)) {
            throw new BusinessException(Code.BAD_REQUEST, "이미 오늘 참여한 활동입니다.");
        }

        // 1. 활동 이력 저장
        activityHistoryService.record(
                userId,
                activity.getId(),
                activity.getPoint()
        );

        pointService.updatePointBalance(userId, activity.getPoint());
    }

    private boolean isAlreadyAttendedToday(ActivityHistoryResponseDto dto) {
        return dto != null && Boolean.TRUE.equals(dto.getAttendStatus());
    }
}
