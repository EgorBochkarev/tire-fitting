package com.netcracker.service;

import com.netcracker.jpa.Order;

public interface OrderService {

    Iterable<Order> getAllOrders();

    Order createOrder(Order Order);

    Order getOrder(int orderId);

    Order updateOrder(int oldOrderId, Order newOrder);

    void deleteOrder(int orderId);

}
