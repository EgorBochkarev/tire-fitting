package com.netcracker.controller;

import com.netcracker.jpa.User;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.GET)
    public User getUser(@PathVariable("user_id") int userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.PATCH)
    public User updateUser(@PathVariable("user_id") int oldUserId, @RequestBody User newUser) {
        return userService.updateUser(oldUserId, newUser);
    }

    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("user_id") int userId){
        userService.deleteUser(userId);
    }

}
