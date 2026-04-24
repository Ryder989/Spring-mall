package com.ryder.springmall.service.impl;

import com.ryder.springmall.dao.UserDao;
import com.ryder.springmall.dto.UserLoginRequest;
import com.ryder.springmall.dto.UserRegisterRequest;
import com.ryder.springmall.model.User;
import com.ryder.springmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    // 註冊帳號
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 檢查註冊的email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null){
            log.warn("該 email {} 已經被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashPassword);

        // 創建email
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer user_Id) {
        return userDao.getUserById(user_Id);
    }

    @Override
    // 登入帳號
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        // 檢查帳號是否存在
        if (user == null){
            log.warn("該 email {} 尚未註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用MD5 生成密碼的雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());


        // 檢查密碼是否正確
        if (user.getPassword().equals(hashPassword)){
            return user;
        }else {
            log.warn("email {} 的密碼不正確",userLoginRequest.getPassword());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
