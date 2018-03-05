package com.netcracker.repository;

import com.netcracker.jpa.StatusHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusesHistoryRepository extends CrudRepository<StatusHistory, Integer> {
}
