package com.example.hackathon.domain.activityhistory.service;


import com.example.hackathon.domain.activityhistory.dto.ActivityHistoryResponseDto;
import com.example.hackathon.domain.activityhistory.entity.ActivityHistory;
import com.example.hackathon.domain.activityhistory.repository.ActivityHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityHistoryService {

    private final ActivityHistoryRepository historyRepository;

    public ActivityHistoryService(ActivityHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public long getTotalActivityHistoryCount(Long userId) {
        return historyRepository.countByUserIdAndAttendStatusTrue(userId);
    }

    public List<Long> getTodayActivityIdsByUser(Long userId) {
        return historyRepository.findTodayAttendedActivityIdsByUser(userId);
    }

    public ActivityHistoryResponseDto getTodayAttendedHistory(
            Long userId,
            Long activityId
    ) {
        LocalDate today = LocalDate.now();

        ActivityHistory history = historyRepository
                .findTopByUserIdAndActivityIdAndTodayOrderByRegDtDesc(userId, activityId, today)
                .orElse(null);

        return toResponseDto(history);
    }

    public void record(
            Long userId,
            Long activityId,
            Integer point
    ) {
        ActivityHistory history = ActivityHistory.builder()
                .userId(userId)
                .activityId(activityId)
                .point(point)
                .attendStatus(true)
                .regId(String.valueOf(userId))
                .build();

        historyRepository.save(history);
    }

    private ActivityHistoryResponseDto toResponseDto(ActivityHistory history) {
        if (history == null)
            return null;

        return new ActivityHistoryResponseDto(
                history.getId(),
                history.getActivityId(),
                history.getUserId(),
                history.getPoint(),
                history.getAttendStatus(),
                history.getRegDt()
        );
    }

}
