package com.example.springdatatransactional.service;

import com.example.springdatatransactional.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto getOrderInfo(Long id);

    List<OrderDto> getAllOrder();

    boolean addOrder(OrderDto orderDto);

    boolean updateOrder(OrderDto orderDto);

    boolean deleteOrder(Long id);

    OrderDto placeOrder(OrderDto orderDto);
}
