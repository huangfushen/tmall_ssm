package com.tmall.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {


    //后台主页
    @RequestMapping("main")
    public String main_page(){
        return "admin/main";
    }
    //登录页面
    @RequestMapping("login")
    public String login_page(){
        return "admin/login";
    }
    //注册页面
    @RequestMapping("register")
    public String register_page(){
        return "admin/register";
    }
/*    //没有权限
    @RequestMapping("unauthorized")
    public String noPerms(){
        return "admin/unauthorized";
    }*/

    @RequestMapping("registerPage")
    public String registerPage(){
        return "fore/register";
    }

    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage(){
        return "fore/registerSuccess";
    }

    @RequestMapping("loginPage")
    public String loginPage(){
        return "fore/login";
    }

    @RequestMapping("forealipay")
    public String alipay(){
        return "fore/alipay";
    }
}
