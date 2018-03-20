package com.netcracker.service;

import com.netcracker.jpa.Order;
import com.netcracker.jpa.Service;

import java.util.List;

public interface ServiceService {

    Iterable<Service> getAllServices();

    boolean createService();

    Service getService(int serviceId);

    boolean updateService(int serviceId, String name, String location, int servingStaff, double totalRating, List<Order> orders);

    boolean deleteService(int serviceId);
}
