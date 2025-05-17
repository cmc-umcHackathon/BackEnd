package com.example.hackathon.domain.category.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long id;
    private String description;
    private String parentCode;
    private Integer sortOrder;
    private LocalDateTime regDt;

}
