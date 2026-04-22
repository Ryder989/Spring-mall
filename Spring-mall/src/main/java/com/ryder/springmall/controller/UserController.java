package com.ryder.springmall.controller;

import com.ryder.springmall.dto.UserRegisterRequest;
import com.ryder.springmall.model.User;
import com.ryder.springmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer user_id = userService.register(userRegisterRequest);
        User user = userService.getUserById(user_id);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
