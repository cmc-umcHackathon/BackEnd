package com.example.hackathon.domain.productpurchasehistory.facade;


import com.example.hackathon.domain.point.service.PointService;
import com.example.hackathon.domain.product.dto.ProductResponseDto;
import com.example.hackathon.domain.product.entity.Product;
import com.example.hackathon.domain.product.repository.ProductRepository;
import com.example.hackathon.domain.product.service.ProductService;
import com.example.hackathon.domain.productpurchasehistory.dto.ProductPurchaseRequestDto;
import com.example.hackathon.domain.productpurchasehistory.entity.ProductPurchaseHistory;
import com.example.hackathon.domain.productpurchasehistory.repository.ProductPurchaseHistoryRepository;
import com.example.hackathon.domain.productpurchasehistory.service.ProductPurchaseHistoryService;
import com.example.hackathon.global.exception.BusinessException;
import com.example.hackathon.global.response.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class ProductPurchaseFacade {

    private final ProductService productService;
    private final ProductPurchaseHistoryService productPurchaseHistoryService;
    private final PointService pointService;

    public ProductPurchaseFacade(ProductService productService, ProductPurchaseHistoryService productPurchaseHistoryService, PointService pointService) {
        this.productService = productService;
        this.productPurchaseHistoryService = productPurchaseHistoryService;
        this.pointService = pointService;
    }

    /**
     * 상품 구매 이력 저장 (포인트 차감 제외)
     */
    @Transactional
    public void purchase(Long userId, ProductPurchaseRequestDto request) {
        ProductResponseDto product = productService.getProductInfo(request.productId());

        int quantity = 1;
        int price = product.price();
        int totalPrice = price * quantity;

        ProductPurchaseHistory history = ProductPurchaseHistory.builder()
                .userId(userId)
                .productId(product.id())
                .quantity(quantity)
                .priceAtTime(price)
                .totalPrice(totalPrice)
                .purchaseDt(LocalDateTime.now())
                .regDt(LocalDateTime.now())
                .regId(String.valueOf(userId))
                .updDt(LocalDateTime.now())
                .build();

        productPurchaseHistoryService.purchase(history);

//        pointService.usingPoint();
    }
}
