package com.netcracker.service;


import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.jpa.Service;
import com.netcracker.jpa.User;

public interface AuthorizationAppService {

    Iterable<AuthorizationApp> getAllProfiles();

    int findProfileId(String login);

    AuthorizationApp registrationProfile(User userId, String login, String password);

    AuthorizationApp registrationProfile(Service serviceId, String login, String password);

}
