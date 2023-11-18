package com.javatpoint;

import Dtos.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {


    @RequestMapping(value = "/user")
    public User hello(User user) {
        return user;
    }
}