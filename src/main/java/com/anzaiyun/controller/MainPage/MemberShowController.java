package com.anzaiyun.controller.MainPage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Main")
public class MemberShowController {

    private Logger logger = Logger.getLogger(MemberShowController.class);

    @RequestMapping("/show")
    public String memberShow(Model model, HttpSession session){

        return "member-show.html";
    }
}
