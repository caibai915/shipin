package com.qf.service;

import com.qf.pojo.User;

public interface UserService {
    User select(User user);

    void updateUser(User user);

    User find(String password);

    void updateUserByPassword(User user);

    void insertUser(User user);

    User selectUserByEmail(String email);

//    User findUserById(User user);
}
