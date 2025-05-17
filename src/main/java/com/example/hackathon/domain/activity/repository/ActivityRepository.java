package com.example.hackathon.domain.activity.repository;


import com.example.hackathon.domain.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByCategoryIdAndIsDisplayedTrueOrderBySortOrderAsc(Long categoryId);

    List<Activity> findByIsDisplayedTrueAndIsTodayActivityTrueOrderBySortOrderAsc();
}
