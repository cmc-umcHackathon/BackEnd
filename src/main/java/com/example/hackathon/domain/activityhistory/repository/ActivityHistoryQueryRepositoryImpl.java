package com.example.hackathon.domain.activityhistory.repository;

import com.example.hackathon.domain.activityhistory.dto.ActivityHistoryWithActivityDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityHistoryQueryRepositoryImpl implements ActivityHistoryQueryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ActivityHistoryWithActivityDto> findAttendedHistoriesWithActivityByUserId(Long userId) {
        return em.createQuery("""
            SELECT new com.example.hackathon.domain.activityhistory.dto.ActivityHistoryWithActivityDto(
                ah.id,
                a.id,
                a.description,
                a.point,
                ah.attendStatus,
                ah.regDt
            )
            FROM ActivityHistory ah
            JOIN Activity a ON ah.activityId = a.id
            WHERE ah.userId = :userId
              AND ah.attendStatus = true
            ORDER BY ah.regDt DESC
            """, ActivityHistoryWithActivityDto.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}


