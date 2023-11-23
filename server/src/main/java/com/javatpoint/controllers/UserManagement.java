package com.javatpoint.controllers;

import com.javatpoint.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pojos.LogInfo;
import pojos.ViewInfo;
import pojos.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
public class UserManagement {


    @RequestMapping(value = "/user/createuser")
    public ResponseEntity<User> createUser(User user) {
        UserManagementService service = new UserManagementService();
        User user1 = service.createUser(user);
        if(user1!=null){
            return ResponseEntity.ok(user1);
        }
        else{
            ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
            return status.body(new User());
        }

    }

    @RequestMapping(value = "/user/modifyuser")
    public ResponseEntity<User> modifyUser(User user) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.modifyUser(user));
    }

    @RequestMapping(value = "/user/deleteuser")
    public ResponseEntity<Integer> deleteUser(int id) {
        UserManagementService service = new UserManagementService();
        User user = new User();
        user.setId(id);
        return ResponseEntity.ok(service.deleteUser(user));
    }

    @RequestMapping(value = "/user/showallusers")
    public ResponseEntity<List<User>> showUser(ViewInfo viewInfo) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.showUsers(viewInfo));
    }
    // tested

    @GetMapping("/user/login")
    public ResponseEntity<User> login(@RequestParam(defaultValue = "email") String email,@RequestParam(defaultValue = "password") String password) {
        UserManagementService service = new UserManagementService();
        LogInfo info = new LogInfo();
        info.setEmail(email);
        info.setPassword(password);
        return ResponseEntity.ok(service.loginUser(info));
    }

    @RequestMapping(value = "/user/logout")
    public ResponseEntity<User> logOut(@RequestParam(defaultValue = "danushka") String email) {
        UserManagementService service = new UserManagementService();
        LogInfo info = new LogInfo();
        info.setEmail(email);
        return ResponseEntity.ok(service.logOut(info));
    }

    @RequestMapping(value = "/viewlogusers")
    public ResponseEntity<List<User>> ViewLogInfo() {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.showLoggingUserInfo());
    }
}