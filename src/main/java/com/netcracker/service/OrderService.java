package com.netcracker.service;

import com.netcracker.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();

    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrder(int orderId);

    OrderDto updateOrder(int oldOrderId, OrderDto newOrder);

    void deleteOrder(int orderId);

}
