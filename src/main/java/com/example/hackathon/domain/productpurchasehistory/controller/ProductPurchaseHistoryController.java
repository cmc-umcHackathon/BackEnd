package com.example.hackathon.domain.productpurchasehistory.controller;

import com.example.hackathon.domain.productpurchasehistory.dto.ProductPurchaseHistoryResponseDto;
import com.example.hackathon.domain.productpurchasehistory.dto.ProductPurchaseRequestDto;
import com.example.hackathon.domain.productpurchasehistory.facade.ProductPurchaseFacade;
import com.example.hackathon.domain.productpurchasehistory.service.ProductPurchaseHistoryService;
import com.example.hackathon.global.auth.annotation.AuthUser;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductPurchaseHistoryController {

    private final ProductPurchaseFacade productPurchaseHistoryService;

    public ProductPurchaseHistoryController(ProductPurchaseFacade productPurchaseHistoryService) {
        this.productPurchaseHistoryService = productPurchaseHistoryService;
    }

    @PostMapping("/purchase")
    public Response<Void> purchaseProduct(
            @AuthUser Long userId,
            @RequestBody ProductPurchaseRequestDto request
    ) {
        productPurchaseHistoryService.purchase(userId, request);
        return Response.ok();
    }

}
