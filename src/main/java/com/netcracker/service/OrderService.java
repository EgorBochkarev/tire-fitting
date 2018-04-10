package com.netcracker.service;

import com.netcracker.dto.OrderWrapper;
import com.netcracker.jpa.Order;

import java.util.List;

public interface OrderService {

//    Iterable<Order> getAllOrders();

    List<OrderWrapper> getAllOrders();

//    Order createOrder(Order Order);

    OrderWrapper createOrder(OrderWrapper orderWrapper);

//    Order getOrder(int orderId);

    OrderWrapper getOrder(int orderId);

//    Order updateOrder(int oldOrderId, Order newOrder);

   OrderWrapper updateOrder(int oldOrderId, OrderWrapper newOrder);

    void deleteOrder(int orderId);

}
