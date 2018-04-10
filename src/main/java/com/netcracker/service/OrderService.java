package com.netcracker.service;

import com.netcracker.dto.OrderWrapper;
import java.util.List;

public interface OrderService {

    List<OrderWrapper> getAllOrders();

    OrderWrapper createOrder(OrderWrapper orderWrapper);

    OrderWrapper getOrder(int orderId);

   OrderWrapper updateOrder(int oldOrderId, OrderWrapper newOrder);

    void deleteOrder(int orderId);

}
