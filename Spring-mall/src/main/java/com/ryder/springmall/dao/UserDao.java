package com.ryder.springmall.dao;

import com.ryder.springmall.dto.UserRegisterRequest;
import com.ryder.springmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer user_id);

    User getUserByEmail(String email);
}
