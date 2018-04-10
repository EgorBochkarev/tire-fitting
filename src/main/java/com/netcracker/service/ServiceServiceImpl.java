package com.netcracker.service;

import com.netcracker.jpa.Order;
import com.netcracker.jpa.Service;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.SpecificationForJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AuthorizationAppRepository authorizationAppRepository;

    @Override
    public Iterable<com.netcracker.jpa.Service> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Service createService(Service service) {
        return servicesRepository.save(service);
    }

    @Override
    public Service getService(int serviceId) {
        return servicesRepository.findOne(serviceId);
    }

    @Override
    public Service updateService(int serviceId, Service newService) {
        Service oldService = servicesRepository.findOne(serviceId);
        if ((newService.getName()!=null)&&(newService.getName()!="")) {
            oldService.setName(newService.getName());
        }
        if ((newService.getLocation()!=null)&&(newService.getLocation()!="")) {
            oldService.setName(newService.getName());
        }
        if ((newService.getServingStaff()>0)){
            oldService.setServingStaff(newService.getServingStaff());
        }
        if ((newService.getTotalRating()>0)){
            oldService.setTotalRating(newService.getTotalRating());
        }
        if ((newService.getOrders()!=null)&&(newService.getOrders().size()>0)){
            oldService.setOrders(newService.getOrders());
        }
        return oldService;
    }

    @Override
    public void deleteService(int serviceId) {
        List<Order> list = servicesRepository.findOne(serviceId).getOrders();
        Service service = servicesRepository.findOne(serviceId);
        for (Order orders: list){
            orders.setService(null);
            service.setOrders(null);
            servicesRepository.save(service);
            ordersRepository.save(orders);
        }
        authorizationAppRepository.delete(authorizationAppRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesService(service))));
    }
}
