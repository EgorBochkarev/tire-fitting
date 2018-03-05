package com.netcracker.repository;

import com.netcracker.jpa.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Order,Integer> {
}
