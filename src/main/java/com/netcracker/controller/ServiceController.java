package com.netcracker.controller;

import com.netcracker.jpa.Order;
import com.netcracker.jpa.Service;
import com.netcracker.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public Iterable<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public boolean createService() {
        return serviceService.createService();
    }

    @RequestMapping(value = "/service/{service_id}", method = RequestMethod.GET)
    public Service getService(@RequestParam int serviceId){
        return serviceService.getService(serviceId);
    }

    @RequestMapping(value = "/service/{service_id}", method = RequestMethod.PATCH)
    public boolean updateService(@RequestParam int serviceId, @RequestParam String name, @RequestParam String location,
                                 @RequestParam int servingStaff, @RequestParam double totalRating, @RequestParam List<Order> orders){
        return serviceService.updateService(serviceId, name, location, servingStaff, totalRating, orders);
    }

}
