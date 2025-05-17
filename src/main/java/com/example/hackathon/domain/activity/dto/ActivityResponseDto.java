package com.example.hackathon.domain.activity.dto;

import lombok.Getter;

@Getter
public class ActivityResponseDto {

    private Long id;
    private String description;
    private int point;
    private int sortOrder;
    private boolean isTodayActivity;
    private boolean isCustom;

    public ActivityResponseDto(
            Long id,
            String description,
            int point,
            int sortOrder,
            boolean isTodayActivity,
            boolean isCustom
    ) {
        this.id = id;
        this.description = description;
        this.point = point;
        this.sortOrder = sortOrder;
        this.isTodayActivity = isTodayActivity;
        this.isCustom = isCustom;
    }

    // Getter 생략 가능 (Lombok 사용 시 @Getter)
}
