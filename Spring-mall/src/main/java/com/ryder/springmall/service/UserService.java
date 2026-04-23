package com.ryder.springmall.service;

import com.ryder.springmall.dto.UserLoginRequest;
import com.ryder.springmall.dto.UserRegisterRequest;
import com.ryder.springmall.model.User;

public interface UserService {

    User getUserById(Integer user_Id);
    Integer register(UserRegisterRequest userRegisterRequest);
    User login(UserLoginRequest userLoginRequest);

}
