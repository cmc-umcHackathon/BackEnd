package com.example.hackathon.domain.category.controller;

import com.example.hackathon.domain.category.dto.CategoryResponseDto;
import com.example.hackathon.domain.category.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 전체 카테고리 조회
    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 특정 상위 카테고리 하위 조회
    @GetMapping("/parent/{parentCode}")
    public List<CategoryResponseDto> getCategoriesByParentCode(@PathVariable String parentCode) {
        return categoryService.getCategoriesByParentCode(parentCode);
    }
}
