package com.netcracker.controller;

import com.netcracker.dto.OrderWrapper;
import com.netcracker.jpa.Order;
import com.netcracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderWrapper> getAllOrders(){
        return orderService.getAllOrders();
    }

    @RequestMapping(method = RequestMethod.POST)
    public OrderWrapper createOrder(@RequestBody OrderWrapper order){
        return orderService.createOrder(order);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.GET)
    public OrderWrapper getOrder(@PathVariable("order_id") int orderId){
        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.PATCH)
    public OrderWrapper updateOrder(@PathVariable("order_id") int oldOrderId, @RequestBody OrderWrapper newOrder){
        return orderService.updateOrder(oldOrderId, newOrder);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("order_id") int orderId){
        orderService.deleteOrder(orderId);
    }
}
