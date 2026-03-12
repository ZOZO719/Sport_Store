package com.store.store.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.order.CreateOrderRequest;
import com.store.store.dtos.order.OrderDto;
import com.store.store.entities.Order;
import com.store.store.entities.User;
import com.store.store.mappers.order.OrderMapper;
import com.store.store.repository.OrderRepo;
import com.store.store.repository.UserRepo;
import com.store.store.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepository;
    private final UserRepo userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(CreateOrderRequest dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderMapper.toEntity(dto);

        order.setUser(user);

        Order saved = orderRepository.save(order);

        return orderMapper.toDto(saved);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderMapper.toDto(order);
    }

    @Override
    public void deleteOrder(Long id) {

        if (!orderRepository.existsById(id))
            throw new RuntimeException("Order not found");

        orderRepository.deleteById(id);
    }
}