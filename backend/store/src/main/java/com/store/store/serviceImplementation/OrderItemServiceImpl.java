package com.store.store.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.orderItem.CreateOrderItemRequest;
import com.store.store.dtos.orderItem.OrderItemDto;
import com.store.store.entities.Order;
import com.store.store.entities.OrderItem;
import com.store.store.mappers.OrderItemMapper;
import com.store.store.repository.OrderItemRepo;
import com.store.store.repository.OrderRepo;
import com.store.store.service.OrderItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepo orderItemRepository;
    private final OrderRepo orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDto createOrderItem(CreateOrderItemRequest dto) {

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderItem item = orderItemMapper.toEntity(dto);

        item.setOrder(order);

        OrderItem saved = orderItemRepository.save(item);

        return orderItemMapper.toDto(saved);
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {

        return orderItemRepository.findAll()
                .stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemDto getOrderItemById(Long id) {

        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        return orderItemMapper.toDto(item);
    }

    @Override
    public void deleteOrderItem(Long id) {

        if (!orderItemRepository.existsById(id))
            throw new RuntimeException("OrderItem not found");

        orderItemRepository.deleteById(id);
    }
}