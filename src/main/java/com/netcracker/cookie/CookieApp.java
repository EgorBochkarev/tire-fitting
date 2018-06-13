package com.netcracker.cookie;

import com.netcracker.dto.AuthorizationAppDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieApp {

    private Cookie userCookie = new Cookie("user", "");

    private Cookie serviceCookie = new Cookie("service", "");

    public void setCookie(AuthorizationAppDto appDto, HttpServletResponse response){
        if (appDto.getUserId() > 0){
            userCookie.setValue(String.valueOf(appDto.getUserId()));
            serviceCookie.setMaxAge(0);
            userCookie.setMaxAge(-1);
        }   else {
            serviceCookie.setValue(String.valueOf(appDto.getServiceId()));
            userCookie.setMaxAge(0);
            serviceCookie.setMaxAge(-1);
        }
        response.addCookie(serviceCookie);
        response.addCookie(userCookie);
    }

    public void deleteCookie(HttpServletResponse response){
        userCookie.setMaxAge(0);
        serviceCookie.setMaxAge(0);
        userCookie.setPath("/");
        serviceCookie.setPath("/");
        response.addCookie(serviceCookie);
        response.addCookie(userCookie);
    }
}
