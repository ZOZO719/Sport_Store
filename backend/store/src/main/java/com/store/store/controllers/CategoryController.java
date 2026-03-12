package com.store.store.controllers;


import java.util.List;

// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.store.store.dtos.CategoryDto;
import com.store.store.dtos.CreateCategory;
import com.store.store.dtos.UpdateCategory;
// import com.store.store.mappers.CategoryMapper;
import com.store.store.serviceImplementation.CategorySerImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategorySerImp categorySerImp;
    // private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categories = categorySerImp.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id ){
       try {
         CategoryDto categoryDto = categorySerImp.getCategoryById(id);

        return ResponseEntity.ok(categoryDto);
       } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }

    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CreateCategory dto , UriComponentsBuilder uriComponentsBuilder) {
        CategoryDto category = categorySerImp.createCategory(dto);
        var uri  =uriComponentsBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id , @RequestBody UpdateCategory updated){
        try {
            CategoryDto categoryDto = categorySerImp.updateCategory(id, updated);
        return ResponseEntity.ok().body(categoryDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id) {

       try {
         categorySerImp.deleteCategory(id);

        return ResponseEntity.noContent().build();
       } catch (Exception e) {

        return ResponseEntity.notFound().build();
    }
    }
}
