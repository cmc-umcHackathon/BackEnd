package com.example.hackathon.domain.activityhistory.repository;


import com.example.hackathon.domain.activityhistory.entity.ActivityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Long> {

    @Query(value = """
    SELECT ah.CATEGORY_ID
    FROM ACTIVITY_HISTORY ah
    WHERE ah.USER_ID = :userId
      AND DATE(ah.REG_DT) = CURRENT_DATE
      AND ah.ATTEND_STATUS = true
    GROUP BY ah.CATEGORY_ID
    ORDER BY MAX(ah.REG_DT) DESC
    """, nativeQuery = true)
    List<Long> findTodayAttendedActivityIdsByUser(Long userId);

    @Query("SELECT ah " +
            "FROM ActivityHistory ah " +
            "WHERE ah.userId = :userId " +
            "AND ah.activityId = :activityId " +
            "AND DATE(ah.regDt) = :today " +
            "AND ah.attendStatus = true " +
            "ORDER BY ah.regDt DESC")
    Optional<ActivityHistory> findTopByUserIdAndActivityIdAndTodayOrderByRegDtDesc(
            Long userId,
            Long activityId,
            LocalDate today);

    long countByUserIdAndAttendStatusTrue(Long userId);

}