package com.netcracker.controller;

import com.netcracker.dto.OrderDto;
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
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @RequestMapping(method = RequestMethod.POST)
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.GET)
    public OrderDto getOrder(@PathVariable("order_id") int orderId){
        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.PATCH)
    public OrderDto updateOrder(@PathVariable("order_id") int oldOrderId, @RequestBody OrderDto newOrder){
        return orderService.updateOrder(oldOrderId, newOrder);
    }

    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("order_id") int orderId){
        orderService.deleteOrder(orderId);
    }
}
