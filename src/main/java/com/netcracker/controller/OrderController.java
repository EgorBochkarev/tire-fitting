package com.netcracker.controller;

import com.netcracker.jpa.Order;
import com.netcracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("order_id") int orderId){
        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.PATCH)
    public Order updateOrder(@PathVariable("order_id") int oldOrderId, @RequestBody Order newOrder){
        return orderService.updateOrder(oldOrderId, newOrder);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("order_id") int orderId){
        orderService.deleteOrder(orderId);
    }
}
