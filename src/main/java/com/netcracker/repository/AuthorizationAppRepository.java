package com.netcracker.repository;

import com.netcracker.jpa.AuthorizationApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationAppRepository extends CrudRepository<AuthorizationApp, Integer> {
}
