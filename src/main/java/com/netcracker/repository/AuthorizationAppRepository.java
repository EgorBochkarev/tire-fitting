package com.netcracker.repository;

import com.netcracker.jpa.AuthorizationApp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationAppRepository extends CrudRepository<AuthorizationApp, Integer> {

    @Query("FROM AuthorizationApp where login = ?1")
    AuthorizationApp findProfileByLogin(@Param("login") String login);

    @Query("FROM AuthorizationApp where user_id = ?1")
    AuthorizationApp findProfileByUser(@Param("user_id") int userId);

    @Query("FROM AuthorizationApp where service_id = ?1")
    AuthorizationApp findProfileByService(@Param("service_id") int serviceId);
}
