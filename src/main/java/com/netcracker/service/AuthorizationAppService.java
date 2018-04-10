package com.netcracker.service;


import com.netcracker.dto.AuthorizationAppWrapper;

import java.util.List;


public interface AuthorizationAppService {

    List<AuthorizationAppWrapper> getAllAuthorizationAppWrappers();

    AuthorizationAppWrapper registrateNewProfile(AuthorizationAppWrapper authorizationAppWrapper);

    AuthorizationAppWrapper getProfile(String login);

}
