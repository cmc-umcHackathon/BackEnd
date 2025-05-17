package com.example.hackathon.domain.productpurchasehistory.repository;


import com.example.hackathon.domain.productpurchasehistory.entity.ProductPurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPurchaseHistoryRepository extends JpaRepository<ProductPurchaseHistory, Long> { }
