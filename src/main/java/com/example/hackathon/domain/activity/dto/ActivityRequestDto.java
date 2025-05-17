package com.example.hackathon.domain.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDto {

    private Long categoryId;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
