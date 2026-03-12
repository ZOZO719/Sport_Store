package com.store.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.orderItem.CreateOrderItemRequest;
import com.store.store.dtos.orderItem.OrderItemDto;

@Service
public interface OrderItemService {
    
    OrderItemDto createOrderItem(CreateOrderItemRequest dto);

    List<OrderItemDto> getAllOrderItems();

    OrderItemDto getOrderItemById(Long id);

    void deleteOrderItem(Long id);

}
