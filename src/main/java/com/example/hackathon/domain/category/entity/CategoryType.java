package com.example.hackathon.domain.category.entity;

public enum CategoryType {

    LIFESTYLE("생활습관"),
    TRANSPORTATION("이동"),
    CONSUMPTION("소비"),
    SOCIAL_ACTION("사회 활동");

    private final String description;


    CategoryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
