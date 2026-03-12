package com.store.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.store.store.dtos.CategoryDto;
import com.store.store.dtos.CreateCategory;
import com.store.store.dtos.UpdateCategory;
import com.store.store.entities.Category;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CreateCategory createCategorydto);
      void updateCategory(UpdateCategory dto, @MappingTarget Category category);
}
