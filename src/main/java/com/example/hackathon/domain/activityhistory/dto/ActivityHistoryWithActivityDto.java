package com.example.hackathon.domain.activityhistory.dto;

import java.time.LocalDateTime;

public record ActivityHistoryWithActivityDto(
        Long historyId,
        Long activityId,
        String description,
        Integer point,
        Boolean attendStatus,
        LocalDateTime regDt
) {}

