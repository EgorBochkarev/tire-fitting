package com.netcracker.service;

import com.netcracker.dto.UserWrapper;
import com.netcracker.jpa.User;

import java.util.List;

public interface UserService {

//    Iterable<User> getAllUsers();

    List<UserWrapper> getAllUsers();

//    User createUser(User user);

    UserWrapper createUser(UserWrapper user);

//    User getUser(int userId);

    UserWrapper getUser(int userId);

//    User updateUser(int oldUserId, User newUser);

    UserWrapper updateUser(int oldUserId, UserWrapper newUser);

    void deleteUser(int userId);

}
