package com.store.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.store.dtos.Review.ReviewDto;
import com.store.store.entities.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    // source = "user.userName" يعني خذ userName من جوا الـ user object
    @Mapping(source = "user.userName", target = "userName")
    ReviewDto toDto(Review review);
}