package com.example.hackathon.domain.activityhistory.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ActivityHistoryResponseDto {
    private Long id;
    private Long activityId;
    private String userId;
    private Integer point;
    private Boolean attendStatus;
    private LocalDateTime regDt;

    public ActivityHistoryResponseDto(Long id, Long activityId, String userId, Integer point, Boolean attendStatus, LocalDateTime regDt) {
        this.id = id;
        this.activityId = activityId;
        this.userId = userId;
        this.point = point;
        this.attendStatus = attendStatus;
        this.regDt = regDt;
    }

}
