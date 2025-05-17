package com.example.hackathon.domain.product.dto;

import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        String name,
        Integer price,
        String imgUrl,
        Integer maxPurchaseLimit,
        Integer stockQty,
        LocalDateTime regDt
) {}