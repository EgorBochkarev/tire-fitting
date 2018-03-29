package com.netcracker.service;

import com.netcracker.jpa.Service;

public interface ServiceService {
    Iterable<Service> getAllServices();
    Service createService(Service service);
    Service getService(int serviceId);
    Service updateService(int serviceId, Service service);
    void deleteService(int serviceId);
}
