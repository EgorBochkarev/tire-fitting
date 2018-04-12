package com.netcracker.service;


import com.netcracker.jpa.AuthorizationApp;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationAppServiceImpl implements AuthorizationAppService {
    @Override
    public Iterable<AuthorizationApp> getAllAuthorizationAppWrappers() {
        return null;
    }

    @Override
    public AuthorizationApp registeringNewProfile(AuthorizationApp authorizationApp) {
        return null;
    }

    @Override
    public AuthorizationApp getProfile(String login) {
        return null;
    }
}
