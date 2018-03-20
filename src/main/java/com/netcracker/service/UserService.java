package com.netcracker.service;

import com.netcracker.jpa.User;

public interface UserService {

    Iterable<User> getAllUsers();

    User createUser(User user);

    User getUser(User user);

    User updateUser(User oldUser, User newUser);

    void deleteUser(User user);

}
