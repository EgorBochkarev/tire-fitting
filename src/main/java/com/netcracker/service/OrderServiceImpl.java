package com.netcracker.service;

import com.netcracker.jpa.Order;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.StatusesRepository;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private StatusesRepository statusesRepository;

    @Override
    public Iterable<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        if (order.getUserId() != null){
            order.setUserId(usersRepository.findOne(order.getUserId().getUserId()));
        }
        order.setStatusId(statusesRepository.findOne(order.getStatusId().getStatusId()));
        return ordersRepository.save(order);
    }

    @Override
    public Order getOrder(int orderId) {
        return ordersRepository.findOne(orderId);
    }

    @Override
    public Order updateOrder(int oldOrderId, Order newOrder) {

        Order oldOrder = ordersRepository.findOne(oldOrderId);

        if ((newOrder.getDescription()) != null && !(newOrder.getDescription().equals(""))){
            oldOrder.setDescription(newOrder.getDescription());
        }
        if (newOrder.getStatusId() != null && statusesRepository.exists(newOrder.getStatusId().getStatusId()) && !oldOrder.getStatusId().equals(newOrder.getStatusId())){
            oldOrder.setStatusId(statusesRepository.findOne(newOrder.getStatusId().getStatusId()));
        }
        if ((newOrder.getRating() >= 0)){
            oldOrder.setRating(newOrder.getRating());
        }
        if (newOrder.getUserId() != null && usersRepository.exists(newOrder.getUserId().getUserId()) && oldOrder.getUserId() == null){
            oldOrder.setUserId(usersRepository.findOne(newOrder.getUserId().getUserId()));
        }
        if (newOrder.getServiceId() != null && servicesRepository.exists(newOrder.getServiceId().getServiceId()) && oldOrder.getServiceId() == null){
            oldOrder.setServiceId(servicesRepository.findOne(newOrder.getServiceId().getServiceId()));
        }
        return ordersRepository.save(oldOrder);
    }

    @Override
    public void deleteOrder(int orderId) {
        ordersRepository.findOne(orderId).setStatusId(null);
        ordersRepository.delete(orderId);
    }
}
