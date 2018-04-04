package com.netcracker.service;

import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.jpa.Order;
import com.netcracker.jpa.Service;
import com.netcracker.jpa.User;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private AuthorizationAppRepository authorizationAppRepository;

    @Autowired
    private OrdersRepository ordersRepository;

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
        Iterable<AuthorizationApp> iterable = authorizationAppRepository.findAll();
        Service service = servicesRepository.findOne(serviceId);
        for (Order orders: list){
            orders.setUserId(null);
            service.setOrders(null);
            servicesRepository.save(service);
            ordersRepository.save(orders);
        }
        for (AuthorizationApp app: iterable){
            if (service.equals(app.getServiceId())){
                app.setServiceId(null);
                authorizationAppRepository.delete(authorizationAppRepository.save(app));
            }
        }
        servicesRepository.delete(serviceId);
    }
}
