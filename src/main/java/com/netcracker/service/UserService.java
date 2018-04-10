package com.netcracker.service;

import com.netcracker.dto.UserWrapper;

import java.util.List;

public interface UserService {

    List<UserWrapper> getAllUsers();

    UserWrapper createUser(UserWrapper user);

    UserWrapper getUser(int userId);

    UserWrapper updateUser(int oldUserId, UserWrapper newUser);

    void deleteUser(int userId);

}
