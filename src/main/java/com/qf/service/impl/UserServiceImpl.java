package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.pojo.UserExample;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User select(User user) {
        return userMapper.select(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User find(String password) {

        return userMapper.find(password);
    }

    @Override
    public void updateUserByPassword(User user) {
        userMapper.updateUserByPassword(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

//    @Override
//    public User findUserById(User user) {
//        return userMapper.selectByEmailAndPassword(user);
//    }
}
