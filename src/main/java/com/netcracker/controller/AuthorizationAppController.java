package com.netcracker.controller;

import com.netcracker.cookie.CookieApp;
import com.netcracker.dto.AuthorizationAppDto;
import com.netcracker.service.AuthorizationAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AuthorizationAppController {

    @Autowired
    private AuthorizationAppService authorizationAppService;

    @Autowired
    private CookieApp cookieApp;

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public List<AuthorizationAppDto> getAllAuthorizationApp(){
        return authorizationAppService.getAllAuthorizationApp();
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public AuthorizationAppDto registeringNewProfile(@RequestBody AuthorizationAppDto appDto, HttpServletResponse response){
        cookieApp.setCookie(appDto, response);
        return authorizationAppService.registeringNewProfile(appDto);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public AuthorizationAppDto findProfileId(@RequestParam String login, @RequestParam String password, HttpServletResponse response){
        AuthorizationAppDto appDto = authorizationAppService.getProfileByLogin(login, password);
        cookieApp.setCookie(appDto, response);
        return appDto;
    }
}
