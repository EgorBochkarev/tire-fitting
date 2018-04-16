package com.netcracker.controller;

import com.netcracker.dto.ServiceDto;
import com.netcracker.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServiceDto createService(@RequestBody ServiceDto serviceDto) {
        return serviceService.createService(serviceDto);
    }

    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.GET)
    public ServiceDto getUser(@PathVariable("service_id") int serviceId) {
        return serviceService.getService(serviceId);
    }

    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.PATCH)
    public ServiceDto updateService(@PathVariable("service_id") int serviceId, @RequestBody ServiceDto newService) {
        return serviceService.updateService(serviceId, newService);
    }

    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteService(@PathVariable("service_id") int serviceId){
        serviceService.deleteService(serviceId);
    }

}
