package com.netcracker.repository;

import com.netcracker.jpa.AuthorizationApp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorizationAppRepository extends CrudRepository<AuthorizationApp, Integer> {

    @Query("FROM AuthorizationApp where login = ?1")
    AuthorizationApp findProfileByLogin(@Param("login") String login);
}
