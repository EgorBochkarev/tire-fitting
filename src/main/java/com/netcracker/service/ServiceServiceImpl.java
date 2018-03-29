package com.netcracker.service;

import com.netcracker.jpa.Service;
import com.netcracker.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServicesRepository servicesRepository;

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
        servicesRepository.delete(serviceId);
    }
}
