package com.netcracker.service;

import com.netcracker.dto.AuthorizationAppWrapper;
import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.SpecificationForJpa;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;

import java.util.LinkedList;
import java.util.List;

@org.springframework.stereotype.Service
public class AuthorizationAppServiceImpl implements AuthorizationAppService {

    @Autowired
    private AuthorizationAppRepository authorizationAppRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public List <AuthorizationAppWrapper> getAllAuthorizationAppWrappers() {
        Iterable<AuthorizationApp> all = authorizationAppRepository.findAll();
        List<AuthorizationAppWrapper> list = new LinkedList<AuthorizationAppWrapper>();
        for (AuthorizationApp app : all){
            if (app.getUser() != null){
                list.add(new AuthorizationAppWrapper(app.getLogin(), app.getPassword(), app.getUser().getUserId(), 0));
            }   else {
                list.add(new AuthorizationAppWrapper(app.getLogin(), app. getPassword(), 0, app.getService().getServiceId()));
            }
        }
        return list;
    }

    @Override
    public AuthorizationAppWrapper registrateNewProfile(AuthorizationAppWrapper authorizationAppWrapper) {
        AuthorizationApp app = new AuthorizationApp(authorizationAppWrapper.getLogin(), authorizationAppWrapper.getPassword());
        if (usersRepository.exists(authorizationAppWrapper.getUserId())) {
            app.setUser(usersRepository.findOne(authorizationAppWrapper.getUserId()));
            authorizationAppRepository.save(app);
            return new AuthorizationAppWrapper(authorizationAppWrapper.getLogin(), authorizationAppWrapper.getPassword(), usersRepository.findOne(authorizationAppWrapper.getUserId()).getUserId(), 0);
        }
        if (servicesRepository.exists(authorizationAppWrapper.getServiceId())) {
            app.setService(servicesRepository.findOne(authorizationAppWrapper.getServiceId()));
            authorizationAppRepository.save(app);
            return new AuthorizationAppWrapper(authorizationAppWrapper.getLogin(), authorizationAppWrapper.getPassword(), 0, servicesRepository.findOne(authorizationAppWrapper.getServiceId()).getServiceId());
        } else return new AuthorizationAppWrapper("error", "error", 0, 0);
    }

    @Override
    public AuthorizationAppWrapper getProfile(String login) {
        AuthorizationApp app = authorizationAppRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesLogin(login)));
        if (app == null){
            return new AuthorizationAppWrapper("error", "error", 0, 0);
        }
        if (app.getService() != null) {
            return new AuthorizationAppWrapper(app.getLogin(), app.getPassword(), 0,app.getService().getServiceId());
        }   else{
            return new AuthorizationAppWrapper(app.getLogin(), app.getPassword(), app.getUser().getUserId(),0);
        }
    }
}
