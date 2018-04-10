package com.netcracker.service;

import com.netcracker.dto.ServiceWrapper;
import com.netcracker.jpa.Order;
import com.netcracker.jpa.Service;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.SpecificationForJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;

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
    public List<ServiceWrapper> getAllServices() {
        Iterable<Service> all = servicesRepository.findAll();
        List<ServiceWrapper> list = new LinkedList<ServiceWrapper>();
        for (Service service: all){
            list.add(new ServiceWrapper(service.getServiceId(), service.getName(), service.getLocation(), service.getServingStaff(), service.getTotalRating()));
        }
        return list;
    }

    @Override
    public ServiceWrapper createService(ServiceWrapper serviceWrapper) {
        Service service = new Service(serviceWrapper.getName(), serviceWrapper.getLocation(), serviceWrapper.getServingStaff());
        servicesRepository.save(service);
        return new ServiceWrapper(service.getServiceId(), service.getName(), service.getLocation(), service.getServingStaff(), service.getTotalRating());
    }

    @Override
    public ServiceWrapper getService(int serviceId) {
        Service service = servicesRepository.findOne(serviceId);
        return new ServiceWrapper(service.getServiceId(), service.getName(), service.getLocation(), service.getServingStaff(), service.getTotalRating());
    }

    @Override
    public ServiceWrapper updateService(int serviceId, ServiceWrapper newService) {
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
        return new ServiceWrapper(oldService.getServiceId(), oldService.getName(), oldService.getLocation(), oldService.getServingStaff(), oldService.getTotalRating());
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
        if (authorizationAppRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesService(service))) == null){
            servicesRepository.delete(service);
        }   else
        authorizationAppRepository.delete(authorizationAppRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesService(service))));
    }
}
