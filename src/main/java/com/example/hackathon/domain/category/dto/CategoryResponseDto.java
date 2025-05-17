package com.example.hackathon.domain.category.dto;

import java.time.LocalDateTime;

public class CategoryResponseDto {

    private Long id;
    private String description;
    private String parentCode;
    private Integer sortOrder;
    private LocalDateTime regDt;

    public CategoryResponseDto(Long id, String description, String parentCode, Integer sortOrder,
                       LocalDateTime regDt) {
        this.id = id;
        this.description = description;
        this.parentCode = parentCode;
        this.sortOrder = sortOrder;
        this.regDt = regDt;
    }
}
