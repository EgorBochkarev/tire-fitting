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

//    @Override
//    public Iterable<Order> getAllOrders() {
//        return ordersRepository.findAll();
//    }
//
//    @Override
//    public Order createOrder(Order order) {
//        if (order.getUser() != null){
//            order.setUser(usersRepository.findOne(order.getUser().getUserId()));
//        }
//        order.setStatus(statusesRepository.findOne(order.getStatus().getStatusId()));
//        return ordersRepository.save(order);
//    }
//
//    @Override
//    public Order getOrder(int orderId) {
//        return ordersRepository.findOne(orderId);
//    }
//
//    @Override
//    public Order updateOrder(int oldOrderId, Order newOrder) {
//
//        Order oldOrder = ordersRepository.findOne(oldOrderId);
//
//        if ((newOrder.getDescription()) != null && !(newOrder.getDescription().equals(""))){
//            oldOrder.setDescription(newOrder.getDescription());
//        }
//        if (newOrder.getStatus() != null && statusesRepository.exists(newOrder.getStatus().getStatusId()) && !oldOrder.getStatus().equals(newOrder.getStatus())){
//            oldOrder.setStatus(statusesRepository.findOne(newOrder.getStatus().getStatusId()));
//        }
//        if ((newOrder.getRating() >= 0)){
//            oldOrder.setRating(newOrder.getRating());
//        }
//        if (newOrder.getUser() != null && usersRepository.exists(newOrder.getUser().getUserId()) && oldOrder.getUser() == null){
//            oldOrder.setUser(usersRepository.findOne(newOrder.getUser().getUserId()));
//        }
//        if (newOrder.getService() != null && servicesRepository.exists(newOrder.getService().getServiceId()) && oldOrder.getService() == null){
//            oldOrder.setService(servicesRepository.findOne(newOrder.getService().getServiceId()));
//        }
//        return ordersRepository.save(oldOrder);
//    }

    @Override
    public List<OrderWrapper> getAllOrders() {
        Iterable<Order> all = ordersRepository.findAll();
        List<OrderWrapper> list = new LinkedList<OrderWrapper>();
        for (Order order: all){
            list.add(new OrderWrapper(order.getStatus().getStatusName(), order.getDescription()));
        }
        return list;
    }

    @Override
    public OrderWrapper createOrder(OrderWrapper orderWrapper) {
        Status status = statusesRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesStatus(orderWrapper.getStatus())));
//        Order order = new Order(orderWrapper.getDescription(), status);
//        order.setStatus(statusesRepository.findOne(order.getStatus().getStatusId()));
        ordersRepository.save(new Order(orderWrapper.getDescription(), status));
        return new OrderWrapper(orderWrapper.getStatus(), orderWrapper.getDescription(), 0, 0, 0);
    }

    @Override
    public OrderWrapper getOrder(int orderId) {
        Order order = ordersRepository.findOne(orderId);
        if (order.getUser() != null && order.getService() != null){
            return new OrderWrapper(order.getStatus().getStatusName(), order.getDescription(),order.getRating(), order.getUser().getUserId(), order.getService().getServiceId());
        }   else
        if (order.getUser() != null && order.getService() == null){
            return new OrderWrapper(order.getStatus().getStatusName(), order.getDescription(), order.getRating(), order.getUser().getUserId(), 0);
        }   else if (order.getUser() == null && order.getService() != null){
            return new OrderWrapper(order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, order.getService().getServiceId());
        }   else return new OrderWrapper(order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, 0);
    }

    @Override
    public OrderWrapper updateOrder(int oldOrderId, OrderWrapper newOrder) {
        Order oldOrder = ordersRepository.findOne(oldOrderId);
        Status status = statusesRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesStatus(newOrder.getStatus())));
        if ((newOrder.getDescription()) != null && !(newOrder.getDescription().equals(""))){
            oldOrder.setDescription(newOrder.getDescription());
        }
        if (newOrder.getStatus() != null && status != null /*&& !oldOrder.getStatus().equals(newOrder.getStatus())*/){
            oldOrder.setStatus(status);
        }
        if ((newOrder.getRating() >= 0)){
            oldOrder.setRating(newOrder.getRating());
        }
        if (newOrder.getUserId() >= 0 && usersRepository.exists(newOrder.getUserId()) && oldOrder.getUser() == null){
            oldOrder.setUser(usersRepository.findOne(newOrder.getUserId()));
        }
        if (newOrder.getServiceId() >= 0 && servicesRepository.exists(newOrder.getServiceId()) && oldOrder.getService() == null){
            oldOrder.setService(servicesRepository.findOne(newOrder.getServiceId()));
        }
        return new OrderWrapper(oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), oldOrder.getUser().getUserId(), oldOrder.getService().getServiceId());
    }

    @Override
    public void deleteOrder(int orderId) {
        ordersRepository.findOne(orderId).setStatus(null);
        ordersRepository.delete(orderId);
    }
}
