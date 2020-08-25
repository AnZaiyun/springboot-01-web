package com.anzaiyun.controller.MainPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Main")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }
}
