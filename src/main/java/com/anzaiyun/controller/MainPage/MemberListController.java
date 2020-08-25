package com.anzaiyun.controller.MainPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Main")
public class MemberListController {

    @RequestMapping("/MemberList")
    public String memberList(Model model){
        return "member-list.html";
    }
}
