package com.store.store.mappers.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.store.dtos.order.CreateOrderRequest;
import com.store.store.dtos.order.OrderDto;
import com.store.store.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toEntity(CreateOrderRequest dto); }