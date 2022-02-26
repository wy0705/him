package com.lmxdawn.him.api.db.controller;

import com.lmxdawn.him.api.db.dao.hbaseImpl.UserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserUtil userUtil;
    @RequestMapping("/")
    public String login(){
        return "/login.html";
    }
    @PostMapping("/test")
    @ResponseBody
    public String UserLogin(String name,String password) throws IOException {
        if (userUtil.loginUser(name, password)==true){
            System.out.println("user成功");
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("/Register")
    public String register(){
        return "/registration.html";
    }
    @PostMapping("/register")
    @ResponseBody
    public String register(String name,String phone,String password) throws IOException {
        userUtil.add(name, phone, password);
        return "注册成功";
    }

    @RequestMapping("/Mapssss")
    public String main(){
        return "/map1.html";
    }
    @PostMapping("/main")
    public String map(){

        return "/map1.html";
    }
    @ResponseBody()
    public String main(String name,String password) throws IOException {
        return "欢迎登录";
    }
}
