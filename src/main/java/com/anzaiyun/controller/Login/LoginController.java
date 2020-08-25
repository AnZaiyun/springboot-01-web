package com.anzaiyun.controller.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"","/index"})
public class LoginController {

    @RequestMapping("")
    public String login(){
        return "login.html";
    }
}
