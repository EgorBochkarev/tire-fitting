package com.netcracker.service;


import com.netcracker.jpa.Order;
import com.netcracker.jpa.Service;
import com.netcracker.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public Iterable<Service> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public boolean createService() {
        servicesRepository.save(new Service());
        return true;
    }

    @Override
    public Service getService(int serviceId) {
        if (servicesRepository.exists(serviceId)) {
            return servicesRepository.findOne(serviceId);
        }
        else
            return null;
    }

    @Override
    public boolean updateService(int serviceId, String name, String location, int servingStaff, double totalRating, List<Order> orders) {
        if (servicesRepository.exists(serviceId)) {
            Service service = servicesRepository.findOne(serviceId);
            service.setName(name);
            service.setLocation(location);
            service.setServingStaff(servingStaff);
            service.setTotalRating(totalRating);
            service.setOrders(orders);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteService(int serviceId) {
        if (servicesRepository.exists(serviceId)) {
            servicesRepository.delete(serviceId);
            return true;
        }
        else
            return false;
    }
}
