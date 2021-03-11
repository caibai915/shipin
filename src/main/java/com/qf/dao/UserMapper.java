package com.qf.dao;

import com.qf.pojo.User;
import com.qf.pojo.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {






    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    User select(User user);


    void updateUser(User user);

    User find(String password);

    void updateUserByPassword(User user);

    void insertUser(User user);

    User selectUserByEmail(String email);
}