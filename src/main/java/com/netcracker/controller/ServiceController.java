package com.netcracker.controller;

import com.netcracker.jpa.Service;
import com.netcracker.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Service createService(@RequestBody Service service) {
        return serviceService.createService(service);
    }

    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.GET)
    public Service getUser(@PathVariable("service_id") int serviceId) {
        return serviceService.getService(serviceId);
    }

    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.PATCH)
    public Service updateService(@PathVariable("service_id") int serviceId, @RequestBody Service newService) {
        return serviceService.updateService(serviceId, newService);
    }

    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteService(@PathVariable("service_id") int serviceId){
        serviceService.deleteService(serviceId);
    }

}
