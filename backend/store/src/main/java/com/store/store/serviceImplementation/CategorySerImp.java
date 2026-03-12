package com.store.store.serviceImplementation;


import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.CategoryDto;
import com.store.store.dtos.CreateCategory;
import com.store.store.dtos.UpdateCategory;
import com.store.store.entities.Category;
import com.store.store.mappers.CategoryMapper;
import com.store.store.repository.CategoryRepo;
import com.store.store.service.CategoryService;

// import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategorySerImp implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CreateCategory dto) {
       Category category = categoryMapper.toEntity(dto);
       Category savedCategory = categoryRepo.save(category);

       return categoryMapper.toDto(savedCategory);
    
    }

    @Override
    public List<CategoryDto> getAllCategories() {
       List<Category> categories = categoryRepo.findAll();
       return categories.stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(()-> new RuntimeException("Category Not Found !!!"));
        return categoryMapper.toDto(category);
    }


    @Override
    public void deleteCategory(Long id) {
 if (!categoryRepo.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepo.deleteById(id);
        
    }

    @Override
    public CategoryDto updateCategory(Long id, UpdateCategory dto) {
       Category category = categoryRepo.findById(id).orElseThrow(()->new RuntimeException("Category not found"));
        categoryMapper.updateCategory(dto, category);
       Category updated = categoryRepo.save(category);
       return categoryMapper.toDto(updated);
    }

    

    
}
