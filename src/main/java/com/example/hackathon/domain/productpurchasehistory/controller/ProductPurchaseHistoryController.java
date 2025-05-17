package com.example.hackathon.domain.productpurchasehistory.controller;

import com.example.hackathon.domain.productpurchasehistory.dto.ProductPurchaseRequestDto;
import com.example.hackathon.domain.productpurchasehistory.facade.ProductPurchaseFacade;
import com.example.hackathon.global.auth.annotation.AuthUser;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductPurchaseHistoryController {

    private final ProductPurchaseFacade productPurchaseHistoryService;

    public ProductPurchaseHistoryController(ProductPurchaseFacade productPurchaseHistoryService) {
        this.productPurchaseHistoryService = productPurchaseHistoryService;
    }

    @Operation(summary = "상품 구매 API", description = "현재 고객 정보를 토대로 상품을 구매합니다.")
    @PostMapping("/purchase")
    public Response<Void> purchaseProduct(
            @AuthUser Long userId,
            @RequestBody ProductPurchaseRequestDto request
    ) {
        productPurchaseHistoryService.purchase(userId, request);
        return Response.ok();
    }

}
