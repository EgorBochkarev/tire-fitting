package com.netcracker.service;

import com.netcracker.dto.OrderDto;
import com.netcracker.jpa.Order;
import com.netcracker.jpa.Status;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.StatusesRepository;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<OrderDto> getAllOrders() {
        Iterable<Order> all = ordersRepository.findAll();
        List<OrderDto> list = new LinkedList<OrderDto>();
        for (Order order: all){
            if (order.getService() != null && order.getUser() != null){
                list.add(new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), order.getUser().getUserId(), order.getService().getServiceId()));
            }   else
            if (order.getService() == null && order.getUser() != null) {
                list.add(new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), order.getUser().getUserId(), 0));
            }   else
            if (order.getUser() == null && order.getService() != null) {
                list.add(new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, order.getService().getServiceId()));
            } else {
                list.add(new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, 0));
            }
        }
        return list;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order(orderDto.getDescription(), statusesRepository.findStatus(orderDto.getStatus()), orderDto.getLocation());
        if (orderDto.getUserId() > 0 && usersRepository.exists(orderDto.getUserId())) {
            order.setUser(usersRepository.findOne(orderDto.getUserId()));
            order = ordersRepository.save(order);
            return new OrderDto(order.getOrderId(), orderDto.getLocation(), orderDto.getStatus(), orderDto.getDescription(), 0, usersRepository.findOne(orderDto.getUserId()).getUserId(), 0);
        }   else{
            order = ordersRepository.save(order);
            return new OrderDto(order.getOrderId(), orderDto.getLocation(), orderDto.getStatus(), orderDto.getDescription(), 0, 0, 0);
        }
    }

    @Override
    public OrderDto getOrder(int orderId) {
        Order order = ordersRepository.findOne(orderId);
        if (order.getUser() != null && order.getService() != null){
            return new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(),order.getRating(), order.getUser().getUserId(), order.getService().getServiceId());
        }   else
        if (order.getUser() != null && order.getService() == null){
            return new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), order.getUser().getUserId(), 0);
        }   else if (order.getUser() == null && order.getService() != null){
            return new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, order.getService().getServiceId());
        }   else return new OrderDto(order.getOrderId(), order.getLocation(), order.getStatus().getStatusName(), order.getDescription(), order.getRating(), 0, 0);
    }

    @Override
    public OrderDto updateOrder(int oldOrderId, OrderDto newOrder) {
        Order oldOrder = ordersRepository.findOne(oldOrderId);
        Status status = statusesRepository.findStatus(newOrder.getStatus());
        if (newOrder.getDescription() != null && !newOrder.getDescription().equals("")){
            oldOrder.setDescription(newOrder.getDescription());
        }
        if (newOrder.getStatus() != null && status != null){
            oldOrder.setStatus(status);
        }
        if (newOrder.getLocation() != null && !newOrder.getLocation().equals("")){
            oldOrder.setLocation(newOrder.getLocation());
        }
        if (newOrder.getRating() > 0){
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
            return new OrderDto(oldOrder.getOrderId(), oldOrder.getLocation(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), oldOrder.getUser().getUserId(), oldOrder.getService().getServiceId());
        }   else if (oldOrder.getService() == null){
            return new OrderDto(oldOrder.getOrderId(), oldOrder.getLocation(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), oldOrder.getUser().getUserId(), 0);
        }   else
        if (oldOrder.getUser() == null){
            return new OrderDto(oldOrder.getOrderId(), oldOrder.getLocation(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), 0, oldOrder.getService().getServiceId());
        }   else {
            return new OrderDto(oldOrder.getOrderId(), oldOrder.getLocation(), oldOrder.getStatus().getStatusName(), oldOrder.getDescription(), oldOrder.getRating(), 0, 0);
        }

    }

    @Override
    public void deleteOrder(int orderId) {
        ordersRepository.findOne(orderId).setStatus(null);
        ordersRepository.delete(orderId);
    }
}
