package com.example.hackathon.domain.activity.dto;

public class ActivityRequestDto {
    private Long categoryId;

    // 기본 생성자 및 Getter/Setter
    public ActivityRequestDto() {}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
