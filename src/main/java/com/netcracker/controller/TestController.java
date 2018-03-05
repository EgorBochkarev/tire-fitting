package com.netcracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Todo delete after creating normal controllers
@RestController
public class TestController {

    @GetMapping("/")
    public String index() {
        return "Привет Гость";
    }
}
