package com.ryder.springmall.service.impl;

import com.ryder.springmall.dao.UserDao;
import com.ryder.springmall.dto.UserRegisterRequest;
import com.ryder.springmall.model.User;
import com.ryder.springmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 檢查註冊的email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null){
            log.warn("該 email {} 已經被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 創建email
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer user_Id) {
        return userDao.getUserById(user_Id);
    }
}
