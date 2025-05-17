package com.example.hackathon.domain.activityhistory.repository;


import com.example.hackathon.domain.activityhistory.dto.ActivityHistoryWithActivityDto;

import java.util.List;

public interface ActivityHistoryQueryRepository {
    List<ActivityHistoryWithActivityDto> findAttendedHistoriesWithActivityByUserId(Long userId);
}
