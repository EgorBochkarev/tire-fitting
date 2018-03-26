package com.netcracker.service;

import com.netcracker.jpa.User;

public interface UserService {

    Iterable<User> getAllUsers();

    User createUser(User user);

    User getUser(int userId);

    User updateUser(int oldUserId, User newUser);

    void deleteUser(int userId);

}
