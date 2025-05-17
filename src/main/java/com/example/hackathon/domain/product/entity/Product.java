package com.example.hackathon.domain.product.entity;

import com.example.hackathon.domain.product.dto.ProductResponseDto;
import com.example.hackathon.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Column(name = "IMG_URL", length = 500)
    private String imgUrl;

    @Builder.Default
    @Column(name = "MAX_PURCHASE_LIMIT", nullable = false)
    private Integer maxPurchaseLimit = 1;

    @Builder.Default
    @Column(name = "STOCK_QTY", nullable = false)
    private Integer stockQty = 0;

    @Column(name = "REG_ID", nullable = false, length = 50)
    private String regId;

    @Column(name = "UPD_ID", length = 50)
    private String updId;

    public ProductResponseDto toResponseDto() {
        return new ProductResponseDto(
                this.id,
                this.name,
                this.price,
                this.imgUrl,
                this.maxPurchaseLimit,
                this.stockQty,
                this.getCreatedAt()
        );
    }
}
