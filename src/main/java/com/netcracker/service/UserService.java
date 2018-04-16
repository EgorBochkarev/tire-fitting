package com.netcracker.service;

import com.netcracker.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto getUser(int userId);

    UserDto updateUser(int oldUserId, UserDto newUser);

    void deleteUser(int userId);

}
