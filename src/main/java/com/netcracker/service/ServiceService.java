package com.netcracker.service;

import com.netcracker.dto.ServiceDto;

import java.util.List;

public interface ServiceService {

    List<ServiceDto> getAllServices();

    ServiceDto createService(ServiceDto serviceDto);

    ServiceDto getService(int serviceId);

    ServiceDto updateService(int serviceId, ServiceDto newService);

    void deleteService(int serviceId);
}
