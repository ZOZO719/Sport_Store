package com.store.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.store.dtos.Review.ReviewDto;
import com.store.store.entities.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    // BUG FIX: was "user.username" (UserDetails interface method) which resolves
    // to the email. The entity field we actually want is "userName".
    @Mapping(source = "user.username", target = "userName")
    ReviewDto toDto(Review review);
}