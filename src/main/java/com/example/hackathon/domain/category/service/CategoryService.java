package com.example.hackathon.domain.category.service;


import com.example.hackathon.domain.category.dto.CategoryResponseDto;
import com.example.hackathon.domain.category.entity.Category;
import com.example.hackathon.domain.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CategoryResponseDto> getCategoriesByParentCode(String parentCode) {
        return categoryRepository.findByParentCode(parentCode)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CategoryResponseDto convertToDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getDescription(),
                category.getParentCode(),
                category.getSortOrder(),
                category.getRegDt()
        );
    }
}
