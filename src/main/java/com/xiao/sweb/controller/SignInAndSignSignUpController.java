package com.xiao.sweb.controller;

import com.xiao.sweb.entity.Users;
import com.xiao.sweb.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInAndSignSignUpController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/signIn")
    @Transactional(propagation = Propagation.SUPPORTS) //事务
    public String signIn() {
        return "sign in";
    }

    @RequestMapping("/signUp")
    @Transactional(propagation = Propagation.REQUIRED) //事务
    public String signUp() {
        usersService.insert(new Users());
        return "signUp";
    }
}
