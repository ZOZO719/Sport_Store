package com.store.store.service;

import java.util.List;

import com.store.store.dtos.order.CreateOrderRequest;
import com.store.store.dtos.order.OrderDto;

public interface OrderService {
   
    OrderDto createOrder(CreateOrderRequest dto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    void deleteOrder(Long id);
}
