package com.netcracker.controller;

import com.netcracker.dto.AuthorizationAppWrapper;
import com.netcracker.service.AuthorizationAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorizationAppController {

    @Autowired
    private AuthorizationAppService authorizationAppService;

    @RequestMapping(value = "/registrate", method = RequestMethod.POST)
    public AuthorizationAppWrapper registrateNewProfile(@RequestBody AuthorizationAppWrapper authorizationAppWrapper){
        return authorizationAppService.registrateNewProfile(authorizationAppWrapper);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public AuthorizationAppWrapper getProfileByLogin(@RequestParam String login){
        return authorizationAppService.getProfile(login);
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public List<AuthorizationAppWrapper> getAllAuthorizationApps(){
        return authorizationAppService.getAllAuthorizationAppWrappers();
    }
}
