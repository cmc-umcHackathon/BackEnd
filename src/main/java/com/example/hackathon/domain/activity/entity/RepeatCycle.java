package com.example.hackathon.domain.activity.entity;

public enum RepeatCycle {
    DAILY("매일"),
    WEEKLY_1_2("주 1~2회"),
    WEEKLY_3("주 3회"),
    WEEKLY_4("주 4회");

    private final String description;

    RepeatCycle(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}