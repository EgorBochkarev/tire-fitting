package com.netcracker.service;

import com.netcracker.jpa.AuthorizationApp;

public interface AuthorizationAppService {

    Iterable<AuthorizationApp> getAllAuthorizationAppWrappers();

    AuthorizationApp registeringNewProfile(AuthorizationApp authorizationApp);

    AuthorizationApp getProfile(String login);

}
