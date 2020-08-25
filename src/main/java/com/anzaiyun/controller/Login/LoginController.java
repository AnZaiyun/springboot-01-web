package com.anzaiyun.controller.Login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"","/index"})
public class LoginController {

    @RequestMapping("")
    public String login(@RequestParam("password") String password, @RequestParam("username") String username,
                        Model model){
        System.out.println("password:"+password+" username:"+username);
        return "login.html";
    }
}
