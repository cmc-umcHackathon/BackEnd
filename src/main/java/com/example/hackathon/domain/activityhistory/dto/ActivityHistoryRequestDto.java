package com.example.hackathon.domain.activityhistory.dto;

public class ActivityHistoryRequestDto {
    private Long categoryId;

    // 기본 생성자 및 Getter/Setter
    public ActivityHistoryRequestDto() {}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
