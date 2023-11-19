package com.javatpoint.controllers;

import com.javatpoint.service.UserManagementService;
import pojos.LogInfo;
import pojos.ViewInfo;
import pojos.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserManagement {


    @RequestMapping(value = "/createuser")
    public ResponseEntity<User> createUser(User user) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.createUser(user));
    }

    @RequestMapping(value = "/modifyuser")
    public ResponseEntity<User> modifyUser(User user) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.modifyUser(user));
    }

    @RequestMapping(value = "/deleteuser")
    public ResponseEntity<Integer> deleteUser(User user) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.deleteUser(user));
    }

    @RequestMapping(value = "/showuser")
    public ResponseEntity<List<User>> showUser(ViewInfo viewInfo) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.showUsers(viewInfo));
    }

    @RequestMapping(value = "/login")
    public ResponseEntity<User> login(LogInfo logInfo) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.loginUser(logInfo));
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity<User> logOut(LogInfo LogInfo) {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.logOut(LogInfo));
    }

    @RequestMapping(value = "/viewlogusers")
    public ResponseEntity<List<User>> ViewLogInfo() {
        UserManagementService service = new UserManagementService();
        return ResponseEntity.ok(service.showLoggingUserInfo());
    }
}