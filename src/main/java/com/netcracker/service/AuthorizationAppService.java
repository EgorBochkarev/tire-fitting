package com.netcracker.service;

import com.netcracker.dto.AuthorizationAppDto;

import java.util.List;

public interface AuthorizationAppService {

    List<AuthorizationAppDto> getAllAuthorizationApp();

    AuthorizationAppDto registeringNewProfile(AuthorizationAppDto authorizationAppDto);

    AuthorizationAppDto getProfileByLogin(String login, String password);

}
