package com.example.hackathon.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private Long categoryId;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
