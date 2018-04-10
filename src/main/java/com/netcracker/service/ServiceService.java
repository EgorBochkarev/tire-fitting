package com.netcracker.service;

import com.netcracker.dto.ServiceWrapper;

import java.util.List;

public interface ServiceService {

    List<ServiceWrapper> getAllServices();

    ServiceWrapper createService(ServiceWrapper service);

    ServiceWrapper getService(int serviceId);

    ServiceWrapper updateService(int serviceId, ServiceWrapper service);

    void deleteService(int serviceId);
}
