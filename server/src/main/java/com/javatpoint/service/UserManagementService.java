package com.javatpoint.service;

import com.javatpoint.dataHandlers.UserManagementController;
import pojos.LogInfo;
import pojos.ViewInfo;
import pojos.User;

import java.io.IOException;
import java.util.List;

public class UserManagementService {

    public User createUser(User user) {

        if (user != null) {

            UserManagementController usermanagement = new UserManagementController();
            return usermanagement.createOrModifyuser(user,true);

        }
        return null;
    }


    public User modifyUser(User user) {

        if (user != null) {

            UserManagementController usermanagement = new UserManagementController();
            return usermanagement.createOrModifyuser(user,false);

        }
        return null;
    }

    public int deleteUser(User user) {

        if (user != null) {

            UserManagementController usermanagement = new UserManagementController();
            return usermanagement.deleteuser(user);

        }
        return 0;
    }


    public List<User> showUsers(ViewInfo viewInfo) {

        if (viewInfo != null) {

            UserManagementController usermanagement = new UserManagementController();
            return usermanagement.showInfo(viewInfo);

        }
        return null;
    }

    public User loginUser(LogInfo logInfo) {
        UserManagementController usermanagement = new UserManagementController();
        return usermanagement.logIn(logInfo);

    }

    public User logOut(LogInfo logInfo) {
        UserManagementController usermanagement = new UserManagementController();
        return usermanagement.logOut(logInfo);
    }

    public List<User> showLoggingUserInfo() {
        UserManagementController usermanagement = new UserManagementController();
        return usermanagement.showLoggingUserInfo();
    }
}
