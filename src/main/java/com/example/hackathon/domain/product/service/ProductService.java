package com.example.hackathon.domain.product.service;


import com.example.hackathon.domain.product.dto.ProductResponseDto;
import com.example.hackathon.domain.product.entity.Product;
import com.example.hackathon.domain.product.repository.ProductRepository;
import com.example.hackathon.global.exception.BusinessException;
import com.example.hackathon.global.response.Code;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto getProductInfo(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Code.PRODUCT_NOT_FOUND))
                .toResponseDto();
    }

    public List<ProductResponseDto> getAvailableProducts() {
        return productRepository.findAll()
                .stream()
                .map(Product::toResponseDto)
                .toList();
    }

}
