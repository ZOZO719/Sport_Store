package com.store.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.store.dtos.orderItem.CreateOrderItemRequest;
import com.store.store.dtos.orderItem.OrderItemDto;
import com.store.store.entities.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "order.id", target = "orderId")
    OrderItemDto toDto(OrderItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(CreateOrderItemRequest dto);

}