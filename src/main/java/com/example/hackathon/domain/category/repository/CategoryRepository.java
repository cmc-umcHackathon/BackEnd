package com.example.hackathon.domain.category.repository;


import com.example.hackathon.domain.category.entity.Category;
import com.example.hackathon.domain.category.entity.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentCode(String parentCode);

    List<Category> findAllByOrderBySortOrderAsc();

    Optional<Category> findByCategoryType(CategoryType categoryType);

}

