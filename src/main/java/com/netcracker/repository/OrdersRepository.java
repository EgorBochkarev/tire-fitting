package com.netcracker.repository;

import com.netcracker.jpa.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Order,Integer> {

    @Query("FROM com.netcracker.jpa.Order where user_id = ?1 AND status_id = 473")
    List<Order> findOrdersByUserId (@Param("user_id") int userId);

    @Query("FROM com.netcracker.jpa.Order where user_id = ?1 AND status_id = 472")
    List<Order> findActiveOrdersByUserId (@Param("user_id") int userId);

    @Query("FROM com.netcracker.jpa.Order where service_id = null AND status_id = 472")
    List<Order> findAvailableOrders();

    @Query("FROM com.netcracker.jpa.Order where service_id = ?1 AND status_id = 472")
    List<Order> findOrdersByServicerId (@Param("service_id") int serviceId);
}
