package com.netcracker.controller;

import com.netcracker.dto.UserDto;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable("user_id") int userId) {
        return userService.getUser(userId);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.PATCH)
    public UserDto updateUser(@PathVariable("user_id") int oldUserId, @RequestBody UserDto newUser) {
        return userService.updateUser(oldUserId, newUser);
    }

    @CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping(value = "/{user_id:[\\d]+}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("user_id") int userId){
        userService.deleteUser(userId);
    }

}
