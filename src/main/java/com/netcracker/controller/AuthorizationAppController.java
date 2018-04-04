package com.netcracker.controller;

import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.jpa.Service;
import com.netcracker.jpa.User;
import com.netcracker.service.AuthorizationAppService;
import com.netcracker.service.ServiceService;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization")
public class AuthorizationAppController {

    @Autowired
    private AuthorizationAppService authorizationAppService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<AuthorizationApp> getAllProfiles(){
        return authorizationAppService.getAllProfiles();
    }

    @RequestMapping(value = "/registration/user", method = RequestMethod.POST)
    public AuthorizationApp registrationUser(@RequestParam String login, @RequestParam String password, @RequestBody User user){
        return authorizationAppService.registrationProfile(userService.createUser(user), login, password);
    }

    @RequestMapping(value = "/registration/service", method = RequestMethod.POST)
    public AuthorizationApp registrationService(@RequestParam String login, @RequestParam String password, @RequestBody Service service){
        return authorizationAppService.registrationProfile(serviceService.createService(service), login, password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public int findProfileId(@RequestParam String login){
        return authorizationAppService.findProfileId(login);
    }

}
