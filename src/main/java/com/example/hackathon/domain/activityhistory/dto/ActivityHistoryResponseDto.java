package com.example.hackathon.domain.activityhistory.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityHistoryResponseDto {

    private Long id;
    private Long activityId;
    private Long userId;
    private Integer point;
    private Boolean attendStatus;
    private LocalDateTime regDt;

}
