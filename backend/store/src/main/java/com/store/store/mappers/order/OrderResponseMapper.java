package com.store.store.mappers.order;

import org.mapstruct.Mapper;

import com.store.store.dtos.order.OrderResponseDto;
import com.store.store.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
        OrderResponseDto toDto(Order order);

}
