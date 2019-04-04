package com.wwn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {

    @RequestMapping("/404.do")
    @ResponseBody
    public String errorPage(){
        return "你要找的页面，被lison偷吃了！";
    }
}
