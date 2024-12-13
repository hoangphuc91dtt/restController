package com.example.buoi1.controller;

import com.example.buoi1.model.UserDemo;
import com.example.buoi1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDemo user) {
        userService.saveOrUpdate(user);
        return "User registered successfully!";
    }
}
