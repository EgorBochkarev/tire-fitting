package com.netcracker.service;


import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class AuthorizationAppServiceImpl implements AuthorizationAppService {
    @Autowired
    private AuthorizationAppRepository authorizationAppRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public Iterable<AuthorizationApp> getAllAuthorizationApp() {
        return authorizationAppRepository.findAll();
    }

    @Override
    public AuthorizationApp registeringNewProfile(AuthorizationApp authorizationApp) {
        if (authorizationApp.getLogin() != null && !"".equals(authorizationApp.getLogin()) && authorizationApp.getPassword() != null && !"".equals(authorizationApp.getPassword())){
            if (authorizationApp.getUser() != null && usersRepository.exists(authorizationApp.getUser().getUserId())) {
                authorizationApp.setLogin(authorizationApp.getLogin().toLowerCase());
                authorizationApp.setUser(usersRepository.findOne(authorizationApp.getUser().getUserId()));
                return authorizationAppRepository.save(authorizationApp);
            }   else
            if (authorizationApp.getService() != null && servicesRepository.exists(authorizationApp.getService().getServiceId())) {
                authorizationApp.setLogin(authorizationApp.getLogin().toLowerCase());
                authorizationApp.setService(servicesRepository.findOne(authorizationApp.getService().getServiceId()));
                return authorizationAppRepository.save(authorizationApp);
            }   else return null;
        }   else return null;
    }

    @Override
    public AuthorizationApp getProfileByLogin(String login, String password) {
        AuthorizationApp app = authorizationAppRepository.findProfileByLogin(login.toLowerCase());
        if (app != null && password.equals(app.getPassword())){
            return app;
        }   else return null;
    }
}
