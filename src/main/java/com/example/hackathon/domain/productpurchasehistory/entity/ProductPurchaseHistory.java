package com.example.hackathon.domain.productpurchasehistory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT_PURCHASE_HISTORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductPurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "PRICE_AT_TIME", nullable = false)
    private Integer priceAtTime;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Integer totalPrice;

    @Column(name = "PURCHASE_DT", nullable = false)
    private LocalDateTime purchaseDt;

    @Column(name = "REG_DT", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "REG_ID", nullable = false, length = 50)
    private String regId;

    @Column(name = "UPD_DT", nullable = false)
    private LocalDateTime updDt;

    @Column(name = "UPD_ID", length = 50)
    private String updId;

    // 필요 시 생성 메서드 등 추가 가능
}
