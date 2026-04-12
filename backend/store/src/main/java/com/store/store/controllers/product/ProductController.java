package com.store.store.controllers.product;

import com.store.store.serviceImplementation.product.ProductSerimpl;
import java.math.BigDecimal;
// import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDetailsDto;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;
// import com.store.store.service.productser.ProductService;

import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
     private final ProductSerimpl productSerimpl;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody CreateProductRequest dto) {

        ProductDto product = productSerimpl.createProduct(dto);

        return ResponseEntity.ok(product);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productSerimpl.getProductById(id)); 
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProductsByFilter(   @RequestParam(required = false) String category,
            @RequestParam(required = false) String itemType,
            @RequestParam(defaultValue = "0") int page,       // ✅ الصفحة الأولى = 0
            @RequestParam(defaultValue = "12") int size,      // ✅ 12 منتج بالصفحة
            @RequestParam(defaultValue = "createdAt") String sort,  // ✅ ترتيب افتراضي
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String brand){
                return ResponseEntity.ok(productSerimpl.getProductByFilter(category, itemType, page, size, sort, minPrice, maxPrice, brand));
            }
   
    

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest dto) {

        return ResponseEntity.ok(productSerimpl.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        productSerimpl.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
