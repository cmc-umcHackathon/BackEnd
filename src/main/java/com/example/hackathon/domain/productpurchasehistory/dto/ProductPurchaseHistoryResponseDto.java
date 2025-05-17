package com.example.hackathon.domain.productpurchasehistory.dto;

import java.time.LocalDateTime;

public record ProductPurchaseHistoryResponseDto(
        Long id,
        String name,
        Integer price,
        String imgUrl,
        Integer maxPurchaseLimit,
        Integer stockQty,
        LocalDateTime regDt
) {}