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
        if (order.getUser() != null){
            order.setUser(usersRepository.findOne(order.getUser().getUserId()));
        }
        order.setStatus(statusesRepository.findOne(order.getStatus().getStatusId()));
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
        if (newOrder.getStatus() != null && statusesRepository.exists(newOrder.getStatus().getStatusId()) && !oldOrder.getStatus().equals(newOrder.getStatus())){
            oldOrder.setStatus(statusesRepository.findOne(newOrder.getStatus().getStatusId()));
        }
        if ((newOrder.getRating() >= 0)){
            oldOrder.setRating(newOrder.getRating());
        }
        if (newOrder.getUser() != null && usersRepository.exists(newOrder.getUser().getUserId()) && oldOrder.getUser() == null){
            oldOrder.setUser(usersRepository.findOne(newOrder.getUser().getUserId()));
        }
        if (newOrder.getService() != null && servicesRepository.exists(newOrder.getService().getServiceId()) && oldOrder.getService() == null){
            oldOrder.setService(servicesRepository.findOne(newOrder.getService().getServiceId()));
        }
        return ordersRepository.save(oldOrder);
    }

    @Override
    public void deleteOrder(int orderId) {
        ordersRepository.findOne(orderId).setStatus(null);
        ordersRepository.delete(orderId);
    }
}
