package com.example.hackathon.domain.activity.dto;

import com.example.hackathon.domain.activity.entity.RepeatCycle;
import com.example.hackathon.domain.category.entity.Category;
import com.example.hackathon.domain.category.entity.CategoryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ActivityNewRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddActivity {

        @Schema(description = "활동 제목")
        private String title;

        @Schema(description = "활동 카테고리 (생활습관, 이동, 소비, 사회활동)")
        private CategoryType categoryType;

        @Schema(description = "활동에 대한 간단한 설명")
        private String description;

        @Schema(description = "반복 주기 (DAILY, WEEKLY_1_2, WEEKLY_3, WEEKLY_4)")
        private RepeatCycle repeatCycle;
    }
}
