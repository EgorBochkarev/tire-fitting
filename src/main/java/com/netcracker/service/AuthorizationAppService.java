package com.netcracker.service;

import com.netcracker.jpa.AuthorizationApp;

public interface AuthorizationAppService {

    Iterable<AuthorizationApp> getAllAuthorizationApp();

    AuthorizationApp registeringNewProfile(AuthorizationApp authorizationApp);

    AuthorizationApp getProfileByLogin(String login, String password);

}
