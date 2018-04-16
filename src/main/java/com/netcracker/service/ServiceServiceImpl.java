package com.netcracker.service;

import com.netcracker.dto.ServiceDto;
import com.netcracker.jpa.Order;
import com.netcracker.jpa.Service;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
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
    public List<ServiceDto> getAllServices() {
        Iterable<Service> all = servicesRepository.findAll();
        List<ServiceDto> list = new LinkedList<ServiceDto>();
        for (Service service: all){
            list.add(new ServiceDto(service.getServiceId(), service.getName(), service.getLocation(), service.getServingStaff(), service.getTotalRating()));
        }
        return list;
    }

    @Override
    public ServiceDto createService(ServiceDto serviceWrapper) {
        Service service = new Service(serviceWrapper.getName(), serviceWrapper.getLocation(), serviceWrapper.getServingStaff());
        servicesRepository.save(service);
        return new ServiceDto(service.getServiceId(), service.getName(), service.getLocation(), service.getServingStaff(), service.getTotalRating());
    }

    @Override
    public ServiceDto getService(int serviceId) {
        Service service = servicesRepository.findOne(serviceId);
        return new ServiceDto(service.getServiceId(), service.getName(), service.getLocation(), service.getServingStaff(), service.getTotalRating());
    }

    @Override
    public ServiceDto updateService(int serviceId, ServiceDto newService) {
        Service oldService = servicesRepository.findOne(serviceId);
        if ((newService.getName()!= null) && !(newService.getName().equals(""))) {
            oldService.setName(newService.getName());
        }
        if ((newService.getLocation()!=null) && !(newService.getLocation().equals(""))) {
            oldService.setName(newService.getName());
        }
        if ((newService.getServingStaff() > 0)){
            oldService.setServingStaff(newService.getServingStaff());
        }
        if ((newService.getTotalRating() > 0)){
            oldService.setTotalRating(newService.getTotalRating());
        }
        servicesRepository.save(oldService);
        return new ServiceDto(oldService.getServiceId(), oldService.getName(), oldService.getLocation(), oldService.getServingStaff(), oldService.getTotalRating());
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
        authorizationAppRepository.delete(authorizationAppRepository.findProfileByService(serviceId));
    }
}
