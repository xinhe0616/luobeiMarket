package com.mao.controller;

import com.mao.entity.AdminUser;
import com.mao.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {



    @Resource
    private AdminUserService adminUserService;


    @RequestMapping({"","/index"})
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping(value = "/login")
    public String adminLogin(
        @RequestParam("name") String name,
        @RequestParam("pwd") String pwd,
        HttpSession session
    ){
            if(StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd))
        {
            session.setAttribute("loginError","用户名或者密码为空");
            System.out.println("用户名或者密码为空");
            return "/login";
        }
            AdminUser user = adminUserService.loginMysql(name, pwd);
                System.out.println(user);
            if (user==null){
                session.setAttribute("loginError","账号或者密码错误");
                System.out.println("账号或者密码错误");
                return "/login";
            }

            return "/index";

    }
}
