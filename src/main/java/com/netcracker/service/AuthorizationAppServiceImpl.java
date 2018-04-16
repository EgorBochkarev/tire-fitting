package com.netcracker.service;


import com.netcracker.dto.AuthorizationAppDto;
import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.ServicesRepository;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
    public List<AuthorizationAppDto> getAllAuthorizationApp() {
        Iterable<AuthorizationApp> all = authorizationAppRepository.findAll();
        List<AuthorizationAppDto> list = new LinkedList<AuthorizationAppDto>();
        for (AuthorizationApp app : all){
            if (app.getUser() != null){
                list.add(new AuthorizationAppDto(app.getLogin(), app.getPassword(), app.getUser().getUserId(), 0));
            }   else {
                list.add(new AuthorizationAppDto(app.getLogin(), app. getPassword(), 0, app.getService().getServiceId()));
            }
        }
        return list;
    }

    @Override
    public AuthorizationAppDto registeringNewProfile(AuthorizationAppDto authorizationAppDto) {
        AuthorizationApp app = new AuthorizationApp(authorizationAppDto.getLogin().toLowerCase(), authorizationAppDto.getPassword());
        if (usersRepository.exists(authorizationAppDto.getUserId())) {
            app.setUser(usersRepository.findOne(authorizationAppDto.getUserId()));
            authorizationAppRepository.save(app);
            return new AuthorizationAppDto(authorizationAppDto.getLogin().toLowerCase(), authorizationAppDto.getPassword(), usersRepository.findOne(authorizationAppDto.getUserId()).getUserId(), 0);
        }
        if (servicesRepository.exists(authorizationAppDto.getServiceId())) {
            app.setService(servicesRepository.findOne(authorizationAppDto.getServiceId()));
            authorizationAppRepository.save(app);
            return new AuthorizationAppDto(authorizationAppDto.getLogin().toLowerCase(), authorizationAppDto.getPassword(), 0, servicesRepository.findOne(authorizationAppDto.getServiceId()).getServiceId());
        } else return null;
    }

    @Override
    public AuthorizationAppDto getProfileByLogin(String login, String password) {
        AuthorizationApp app = authorizationAppRepository.findProfileByLogin(login.toLowerCase());
        if (app == null || !password.equals(app.getPassword())){
            return null;
        }
        if (app.getService() != null) {
            return new AuthorizationAppDto(app.getLogin(), app.getPassword(), 0,app.getService().getServiceId());
        }   else{
            return new AuthorizationAppDto(app.getLogin(), app.getPassword(), app.getUser().getUserId(),0);
        }
    }
}
