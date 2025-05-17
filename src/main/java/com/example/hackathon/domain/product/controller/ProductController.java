package com.example.hackathon.domain.product.controller;

import com.example.hackathon.domain.product.dto.ProductRequestDto;
import com.example.hackathon.domain.product.dto.ProductResponseDto;
import com.example.hackathon.domain.product.service.ProductService;
import com.example.hackathon.global.auth.annotation.AuthUser;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "상품 조회 API", description = "포인트로 구매 가능한 상품을 조회합니다.")
    @GetMapping
    public Response<List<ProductResponseDto>> getAvailableProducts() {
        return Response.ok(productService.getAvailableProducts());
    }

}
