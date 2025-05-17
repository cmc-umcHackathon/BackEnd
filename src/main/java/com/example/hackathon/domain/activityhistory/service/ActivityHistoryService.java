package com.example.hackathon.domain.activityhistory.service;


import com.example.hackathon.domain.activityhistory.repository.ActivityHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityHistoryService {

    private final ActivityHistoryRepository historyRepository;

    public ActivityHistoryService(ActivityHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<Long> getTodayActivityIdsByUser(String userId) {
        LocalDate today = LocalDate.now();
        return historyRepository.findTodayActivityIdsByUser(userId, today);
    }
}
