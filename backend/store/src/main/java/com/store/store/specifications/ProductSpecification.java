package com.store.store.specifications;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.store.store.entities.Product;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {
    public static Specification<Product> withFilters( String category,
            String itemType,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String brand) {
        return (root , query , cb)-> {
            List<Predicate> predicates = new ArrayList<>();
            if (category != null && !category.isBlank())
                predicates.add(cb.equal(root.get("category").get("categoryName"), category));

            // root.get("itemType") → حقل مباشر بالـ Product
            if (itemType != null && !itemType.isBlank())
                predicates.add(cb.equal(root.get("itemType"), itemType));

            if (brand != null && !brand.isBlank())
                predicates.add(cb.equal(root.get("brand"), brand));

            if (minPrice != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get("productPrice"), minPrice));

            if (maxPrice != null)
                predicates.add(cb.lessThanOrEqualTo(root.get("productPrice"), maxPrice));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
