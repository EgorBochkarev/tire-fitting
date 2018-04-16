package com.netcracker.controller;

import com.netcracker.dto.AuthorizationAppDto;
import com.netcracker.service.AuthorizationAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AuthorizationAppController {

    @Autowired
    private AuthorizationAppService authorizationAppService;

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public List<AuthorizationAppDto> getAllAuthorizationApp(){
        return authorizationAppService.getAllAuthorizationApp();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public AuthorizationAppDto registeringNewProfile(@RequestBody AuthorizationAppDto app){
        return authorizationAppService.registeringNewProfile(app);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public AuthorizationAppDto findProfileId(@RequestParam String login, @RequestParam String password, HttpServletResponse response){
        AuthorizationAppDto app = authorizationAppService.getProfileByLogin(login, password);
        if (app.getUserId() > 0){
            response.addCookie(new Cookie("user", String.valueOf(app.getUserId())));
        }   else {
            response.addCookie(new Cookie("service", String.valueOf(app.getServiceId())));
        }
        return app;
    }

}
