package com.javatpoint.dataHandlers;

import pojos.LogInfo;
import pojos.ViewInfo;
import pojos.User;

import java.util.ArrayList;
import java.util.List;


public class UserManagementController {


    public int deleteuser(User user) {
        int status = 0;
        List<User> allusers = new ArrayList<>(Config.system_user);
        for (User user1 : allusers) {
            if (user1.getId() == user.getId()) {
                Config.system_user.remove(user1);
                status = 1;
            }
        }
        return status;
    }


    public User createOrModifyuser(User user, boolean isCreate) {
        List<User> allusers = new ArrayList<>(Config.system_user);
        if (allusers.isEmpty()) {
            Config.system_user.add(user);
        }
        else {
            if (isCreate) {
                for (User user1 : allusers) {
                    if (user1.getId() == user.getId()) {
                        Config.system_user.remove(user1);
                        Config.system_user.add(user);
                        return user;
                    }

                }
            } else {
                for (User user1 : allusers) {
                    if (user1.getId() == user.getId()) {
                        Config.system_user.remove(user1);
                        Config.system_user.add(user);
                        return user;
                    }
                }
            }
        }
        return user;
    }

    public List<User> showInfo(ViewInfo viewInfo) {
        List<User> myList = new ArrayList<>();
        try {


            if (viewInfo.getShowType() == 1) {
                myList.addAll(loadUsers("staff"));
            } else if (viewInfo.getShowType() == 2) {
                myList.addAll(loadUsers("customer"));
            } else {
                myList.addAll(loadUsers());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return myList;
    }

    private List<User> loadUsers(String role) {
        List<User> myList = new ArrayList<>();
        List<User> allusers = new ArrayList<>(Config.system_user);
        for (User user : allusers) {
            if (user.getRole() != null && user.getRole().equalsIgnoreCase(role)) {
                myList.add(user);
            }
        }
        return myList;
    }

    private List<User> loadUsers() {
        List<User> allusers = new ArrayList<>(Config.system_user);
        return new ArrayList<>(allusers);
    }

    public User logIn(LogInfo logInfo) {
        List<User> users = showInfo(new ViewInfo());
        final User[] selectedUser = {null};
        users.forEach(o -> {
            if (logInfo.getEmail().equalsIgnoreCase(o.getEmail()) && logInfo.getPassword().equalsIgnoreCase(o.getPassword())) {
                selectedUser[0] = o;
            }
        });
        User user = selectedUser[0];
        tempCacheLevel.loginInfo.add(user);
        return user;
    }

    public User logOut(LogInfo logInfo) {
        final User[] selectedUser = {null};
        tempCacheLevel.loginInfo.forEach(o -> {
            if (logInfo.getEmail().equalsIgnoreCase(o.getEmail())) {
                selectedUser[0] = o;
            }
        });
        User user = selectedUser[0];
        tempCacheLevel.loginInfo.remove(user);
        return user;
    }

    public List<User> showLoggingUserInfo() {
        return tempCacheLevel.loginInfo;
    }

    public User deleteuser(LogInfo logInfo) {
        final User[] selectedUser = {null};
        Config.system_user.forEach(o -> {
            if (logInfo.getEmail().equalsIgnoreCase(o.getEmail())) {
                selectedUser[0] = o;
            }
        });
        User user = selectedUser[0];
        tempCacheLevel.loginInfo.remove(user);
        return user;
    }
}
