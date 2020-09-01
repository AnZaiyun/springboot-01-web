package com.anzaiyun.controller.MainPage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Main")
public class MemberListController {

    private Logger logger = Logger.getLogger(MemberListController.class);

    @RequestMapping("/MemberList")
    public String memberList(Model model, HttpSession session){

        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        logger.info("会员列表页面  用户：["+username+"] 密码：["+password+"]");

        return "member-list.html";
    }
}
