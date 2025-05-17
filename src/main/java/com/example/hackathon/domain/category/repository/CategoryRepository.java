package com.example.hackathon.domain.category.repository;


import com.example.hackathon.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentCode(String parentCode);

    List<Category> findAllByOrderBySortOrderAsc();
}

