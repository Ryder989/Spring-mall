package com.ryder.springmall.service.impl;

import com.ryder.springmall.dao.UserDao;
import com.ryder.springmall.dto.UserRegisterRequest;
import com.ryder.springmall.model.User;
import com.ryder.springmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer user_Id) {
        return userDao.getUserById(user_Id);
    }
}
