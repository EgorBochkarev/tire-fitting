package com.netcracker.service;

import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.jpa.User;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationAppServiceImpl implements AuthorizationAppService {

    @Autowired
    private AuthorizationAppRepository authorizationAppRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ServicesRepository servicesRepository;


    @Override
    public Iterable<AuthorizationApp> getAllProfiles() {
        return authorizationAppRepository.findAll();
    }

    @Override
    public int findProfileId(String login) {
        Iterable<AuthorizationApp> iterable = authorizationAppRepository.findAll();
        int profileId = 0;
        for (AuthorizationApp app : iterable){
            if (login.equals(app.getLogin())){
                if (app.getUserId() != null){
                    profileId = usersRepository.findOne(app.getUserId().getUserId()).getUserId();
                }   else
                if (app.getServiceId() != null){
                    profileId = servicesRepository.findOne(app.getServiceId().getServiceId()).getServiceId();
                }
            }
       }

       return profileId;
    }

    @Override
    public AuthorizationApp registrationProfile(User userId, String login, String password) {
        AuthorizationApp authorizationApp = new AuthorizationApp(login, password);
        authorizationApp.setUserId(userId);
        return authorizationAppRepository.save(authorizationApp);
    }

    @Override
    public AuthorizationApp registrationProfile(com.netcracker.jpa.Service serviceId, String login, String password) {
        AuthorizationApp authorizationApp = new AuthorizationApp(login, password);
        authorizationApp.setServiceId(serviceId);
        return authorizationAppRepository.save(authorizationApp);
    }
}
