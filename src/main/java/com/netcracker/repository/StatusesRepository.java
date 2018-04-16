package com.netcracker.repository;

import com.netcracker.jpa.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusesRepository extends CrudRepository<Status, Integer> {

    @Query("FROM Status where status_name = ?1")
    Status findStatus(@Param("status_name") String status);
}
