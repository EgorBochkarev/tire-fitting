package com.netcracker.controller;

import com.netcracker.cookie.CookieApp;
import com.netcracker.dto.ServiceDto;
import com.netcracker.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CookieApp cookieApp;

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.GET)
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices();
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.POST)
    public ServiceDto createService(@RequestBody ServiceDto serviceDto) {
        return serviceService.createService(serviceDto);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.GET)
    public ServiceDto getService(@PathVariable("service_id") int serviceId) {
        return serviceService.getService(serviceId);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.PATCH)
    public ServiceDto updateService(@PathVariable("service_id") int serviceId, @RequestBody ServiceDto newService) {
        return serviceService.updateService(serviceId, newService);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{service_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteService(@PathVariable("service_id") int serviceId, HttpServletResponse response){
        serviceService.deleteService(serviceId);
        cookieApp.deleteCookie(response);
    }

}
