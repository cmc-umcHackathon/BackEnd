package com.example.hackathon.domain.activity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponseDto {

    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private int point;
    private int sortOrder;
    private boolean isCustom;

    private boolean isTodayActivity = false;

    public ActivityResponseDto(
            Long id,
            String title,
            String subtitle,
            String description,
            int point,
            int sortOrder,
            boolean isCustom
    ) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.point = point;
        this.sortOrder = sortOrder;
        this.isCustom = isCustom;
    }

}
