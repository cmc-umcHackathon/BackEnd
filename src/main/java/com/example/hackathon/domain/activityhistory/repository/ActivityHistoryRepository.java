package com.example.hackathon.domain.activityhistory.repository;


import com.example.hackathon.domain.activityhistory.entity.ActivityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Long> {

    @Query("SELECT ah.activityId FROM ActivityHistory ah " +
            "WHERE ah.userId = :userId AND DATE(ah.regDt) = :today AND ah.attendStatus = true")
    List<Long> findTodayActivityIdsByUser(String userId, LocalDate today);

    List<ActivityHistory> findByUserId(String userId);
}