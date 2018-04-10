package com.netcracker.controller;

import com.netcracker.dto.UserWrapper;
import com.netcracker.jpa.User;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserWrapper> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserWrapper createUser(@RequestBody UserWrapper user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.GET)
    public UserWrapper getUser(@PathVariable("user_id") int userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.PATCH)
    public UserWrapper updateUser(@PathVariable("user_id") int oldUserId, @RequestBody UserWrapper newUser) {
        return userService.updateUser(oldUserId, newUser);
    }

    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("user_id") int userId){
        userService.deleteUser(userId);
    }

}
