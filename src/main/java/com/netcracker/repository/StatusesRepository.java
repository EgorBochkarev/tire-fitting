package com.netcracker.repository;

import com.netcracker.jpa.Status;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusesRepository extends CrudRepository<Status, Integer>, JpaSpecificationExecutor<Status> {
}
