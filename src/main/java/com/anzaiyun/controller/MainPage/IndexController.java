package com.anzaiyun.controller.MainPage;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Main")
public class IndexController {

    private Logger logger = Logger.getLogger(IndexController.class);


    @RequestMapping("/index")
    public String index(Authentication auth,
                        Model model, HttpSession session){

        User user  = (User) auth.getPrincipal();
        String username = user.getUsername() ;
        String password = (String) auth.getCredentials();
        String authority = auth.getAuthorities().toString();
        logger.info("欢迎页面  用户：["+username+"]  密码：["+password+"]"+"  权限：["+authority+"]");


        return "index.html";

    }
}
