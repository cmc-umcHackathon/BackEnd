package com.example.hackathon.domain.activityhistory.dto;

public class ActivityHistoryRequestDto {

    private Long activityId;
    private boolean isAttend = false;

    // 기본 생성자 및 Getter/Setter
    public ActivityHistoryRequestDto() {}

    public Long getCategoryId() {
        return activityId;
    }

    public void setCategoryId(Long activityId) {
        this.activityId = activityId;
    }
}
