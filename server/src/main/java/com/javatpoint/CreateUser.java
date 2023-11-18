package com.javatpoint;

import Dtos.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {


    @RequestMapping(value = "/user")
    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(user);
    }
}