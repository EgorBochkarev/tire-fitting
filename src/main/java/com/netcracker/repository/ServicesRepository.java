package com.netcracker.repository;

import com.netcracker.jpa.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends CrudRepository<Service,Integer> {
}
