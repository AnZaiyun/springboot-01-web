package com.anzaiyun.controller.MainPage;

import org.apache.log4j.Logger;
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
    public String index(@RequestParam(value = "password",required = false) String password,
                        @RequestParam(value = "username",required = false) String username,
                        Model model, HttpSession session){

        logger.info("欢迎页面  用户：["+username+"] 密码：["+password+"]");

        session.setAttribute("username",username);
        session.setAttribute("password",password);

        return "index.html";

    }
}
