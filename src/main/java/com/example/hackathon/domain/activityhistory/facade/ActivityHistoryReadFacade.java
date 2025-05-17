package com.example.hackathon.domain.activityhistory.facade;

import com.example.hackathon.domain.activityhistory.dto.ActivityHistoryWithActivityDto;
import com.example.hackathon.domain.activityhistory.repository.ActivityHistoryQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityHistoryReadFacade {

    private final ActivityHistoryQueryRepository historyQueryRepository;

    public ActivityHistoryReadFacade(ActivityHistoryQueryRepository historyQueryRepository) {
        this.historyQueryRepository = historyQueryRepository;
    }

    /**
     * 활동 이력 + 활동 정보 조합 조회
     */
    public List<ActivityHistoryWithActivityDto> getUserAttendedHistoryWithActivity(Long userId) {
        return historyQueryRepository.findAttendedHistoriesWithActivityByUserId(userId);
    }
}
