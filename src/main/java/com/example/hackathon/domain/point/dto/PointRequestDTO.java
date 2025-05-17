package com.example.hackathon.domain.point.dto;

import com.example.hackathon.domain.activity.entity.RepeatCycle;
import com.example.hackathon.domain.category.entity.Category;
import com.example.hackathon.domain.point.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PointRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class buyProductReq{

        private ProductType productType;

    }
}
