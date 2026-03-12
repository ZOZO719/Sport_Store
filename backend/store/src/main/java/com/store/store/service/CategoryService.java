package com.store.store.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.CategoryDto;
import com.store.store.dtos.CreateCategory;
import com.store.store.dtos.UpdateCategory;

@Service
public interface CategoryService {
    CategoryDto createCategory(CreateCategory dto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    void deleteCategory(Long id);
    CategoryDto updateCategory(Long id, UpdateCategory dto);
}
