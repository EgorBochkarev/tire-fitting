package com.netcracker.controller;

import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.service.AuthorizationAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthorizationAppController {

    @Autowired
    private AuthorizationAppService authorizationAppService;

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public Iterable<AuthorizationApp> getAllAuthorizationApp(){
        return authorizationAppService.getAllAuthorizationApp();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public AuthorizationApp registeringNewProfile(@RequestBody AuthorizationApp app){
        return authorizationAppService.registeringNewProfile(app);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public AuthorizationApp findProfileId(@RequestParam String login, @RequestParam String password, HttpServletResponse response){
        AuthorizationApp app = authorizationAppService.getProfileByLogin(login, password);
        if (app.getUser() != null){
            response.addCookie(new Cookie("user", String.valueOf(app.getUser().getUserId())));
        }   else {
            response.addCookie(new Cookie("service", String.valueOf(app.getService().getServiceId())));
        }
        return app;
    }

}
