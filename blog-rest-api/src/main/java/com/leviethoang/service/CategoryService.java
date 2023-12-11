package com.leviethoang.service;

import com.leviethoang.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategoryById(CategoryDto categoryDto, Long id);

    void deleteCategoryById(Long id);

}
