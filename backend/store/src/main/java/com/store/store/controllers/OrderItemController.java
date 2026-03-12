package com.store.store.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.store.dtos.orderItem.CreateOrderItemRequest;
import com.store.store.dtos.orderItem.OrderItemDto;
import com.store.store.serviceImplementation.OrderItemServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemServiceImpl orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody CreateOrderItemRequest dto) {

        return ResponseEntity.ok(orderItemService.createOrderItem(dto));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {

        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Long id) {

        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {

        orderItemService.deleteOrderItem(id);

        return ResponseEntity.noContent().build();
    }
}