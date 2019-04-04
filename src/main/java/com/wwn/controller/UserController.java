package com.wwn.controller;

import com.wwn.model.Users;
import com.wwn.service.UserService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String home(){
        return "hello";
    }

    @RequestMapping("/login")
    public String login(String username,String passwd){
        boolean login = userService.login(username,passwd);
        if(login){
            logger.info("登陆成功");
            return "登陆成功";
        }else{
            return  "登陆失败";
        }
    }


    @RequestMapping("/register")
    public String register(String username,String passwd){
        boolean login = userService.register(username,passwd);
        if(login){
            return "注册成功";
        }else{
            return  "注册失败";
        }
    }

    @RequestMapping("/batchAdd")
    public String batchAdd(String username,String passwd) {
        userService.batchAdd(username, passwd);
        return "成功";
    }
}
