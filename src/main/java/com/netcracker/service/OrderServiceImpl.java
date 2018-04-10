package com.netcracker.service;

import com.netcracker.dto.OrderWrapper;
import com.netcracker.jpa.Order;
import com.netcracker.jpa.Status;
import com.netcracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


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
    public List<OrderWrapper> getAllOrders() {
        Iterable<Order> all = ordersRepository.findAll();
        List<OrderWrapper> list = new LinkedList<OrderWrapper>();
        for (Order order: all){
            if (order.getService() != null && order.getUser() != null){
                list.add(new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), order.getUser().getUserId(), order.getService().getServiceId()));
            }   else
            if (order.getService() == null && order.getUser() != null) {
                    list.add(new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), order.getUser().getUserId(), 0));
            }   else
            if (order.getUser() == null && order.getService() != null) {
                    list.add(new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, order.getService().getServiceId()));
            } else {
                    list.add(new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, 0));
            }
        }
        return list;
    }

    @Override
    public OrderWrapper createOrder(OrderWrapper orderWrapper) {
        Status status = statusesRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesStatus(orderWrapper.getStatus())));
        Order order = new Order(orderWrapper.getDescription(), usersRepository.findOne(orderWrapper.getUserId()), status);
        if (orderWrapper.getUserId() > 0 && usersRepository.exists(orderWrapper.getUserId())) {
            order = ordersRepository.save(order);
            return new OrderWrapper(order.getOrderId(), orderWrapper.getStatus(), orderWrapper.getDescription(), 0, usersRepository.findOne(orderWrapper.getUserId()).getUserId(), 0);
        }   else{
            order = ordersRepository.save(order);
            return new OrderWrapper(order.getOrderId(), orderWrapper.getStatus(), orderWrapper.getDescription(), 0, 0, 0);
        }
    }

    @Override
    public OrderWrapper getOrder(int orderId) {
        Order order = ordersRepository.findOne(orderId);
        if (order.getUser() != null && order.getService() != null){
            return new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(),order.getRating(), order.getUser().getUserId(), order.getService().getServiceId());
        }   else
        if (order.getUser() != null && order.getService() == null){
            return new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), order.getUser().getUserId(), 0);
        }   else if (order.getUser() == null && order.getService() != null){
            return new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, order.getService().getServiceId());
        }   else return new OrderWrapper(order.getOrderId(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, 0);
    }

    @Override
    public OrderWrapper updateOrder(int oldOrderId, OrderWrapper newOrder) {
        Order oldOrder = ordersRepository.findOne(oldOrderId);
        Status status = statusesRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesStatus(newOrder.getStatus())));
        if ((newOrder.getDescription()) != null && !(newOrder.getDescription().equals(""))){
            oldOrder.setDescription(newOrder.getDescription());
        }
        if (newOrder.getStatus() != null && status != null){
            oldOrder.setStatus(status);
        }
        if ((newOrder.getRating() > 0)){
            oldOrder.setRating(newOrder.getRating());
        }
        if (newOrder.getUserId() > 0 && usersRepository.exists(newOrder.getUserId()) && oldOrder.getUser() == null){
            oldOrder.setUser(usersRepository.findOne(newOrder.getUserId()));
        }
        if (newOrder.getServiceId() > 0 && servicesRepository.exists(newOrder.getServiceId()) && oldOrder.getService() == null){
            oldOrder.setService(servicesRepository.findOne(newOrder.getServiceId()));
        }
        ordersRepository.save(oldOrder);
        oldOrder = ordersRepository.findOne(oldOrderId);
        if (oldOrder.getService() != null && oldOrder.getUser() != null){
            return new OrderWrapper(oldOrder.getOrderId(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), oldOrder.getUser().getUserId(), oldOrder.getService().getServiceId());
        }   else if (oldOrder.getService() == null){
            return new OrderWrapper(oldOrder.getOrderId(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), oldOrder.getUser().getUserId(), 0);
        }   else
        if (oldOrder.getUser() == null){
            return new OrderWrapper(oldOrder.getOrderId(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), 0, oldOrder.getService().getServiceId());
        }   else {
            return new OrderWrapper(oldOrder.getOrderId(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), 0, 0);
        }

    }

    @Override
    public void deleteOrder(int orderId) {
        ordersRepository.findOne(orderId).setStatus(null);
        ordersRepository.delete(orderId);
    }
}
