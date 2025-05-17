package com.example.hackathon.domain.productpurchasehistory.service;


import com.example.hackathon.domain.productpurchasehistory.dto.ProductPurchaseHistoryResponseDto;
import com.example.hackathon.domain.productpurchasehistory.entity.ProductPurchaseHistory;
import com.example.hackathon.domain.productpurchasehistory.repository.ProductPurchaseHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPurchaseHistoryService {

    private final ProductPurchaseHistoryRepository productPurchaseHistoryRepository;

    public ProductPurchaseHistoryService(ProductPurchaseHistoryRepository productPurchaseHistoryRepository) {
        this.productPurchaseHistoryRepository = productPurchaseHistoryRepository;
    }

    public ProductPurchaseHistory purchase(ProductPurchaseHistory productPurchaseHistory) {
        return productPurchaseHistoryRepository.save(productPurchaseHistory);
    }

}
