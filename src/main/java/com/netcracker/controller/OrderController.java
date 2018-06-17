package com.netcracker.controller;
import com.netcracker.dto.OrderDto;
import com.netcracker.dto.UserDto;
import com.netcracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.POST)
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.GET)
    public OrderDto getOrder(@PathVariable("order_id") int orderId){
        return orderService.getOrder(orderId);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.PUT)
    public OrderDto updateOrder(@PathVariable("order_id") int oldOrderId, @RequestBody OrderDto newOrder){
        return orderService.updateOrder(oldOrderId, newOrder);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{order_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("order_id") int orderId){
        orderService.deleteOrder(orderId);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/history/{user_id:[\\d]+}" ,method = RequestMethod.GET)
    public List<OrderDto> findOrdersByUserId (@PathVariable("user_id") int userId){
        return orderService.getOrdersByUserId(userId);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/complete/{service_id:[\\d]+}" ,method = RequestMethod.GET)
    public List<OrderDto> findOrdersByServiceId (@PathVariable("service_id") int serviceId){
        return orderService.getOrdersByServiceId(serviceId);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/active/{user_id:[\\d]+}" ,method = RequestMethod.GET)
    public List<OrderDto> findActiveOrder (@PathVariable("user_id") int userId){
        return orderService.getActiveOrder(userId);
    }
    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/available/{service_id:[\\d]+}" ,method = RequestMethod.GET)
    public List<OrderDto> findAvailableOrders (@PathVariable("service_id") int userId){
        return orderService.getCurrentOrders();
    }

}
