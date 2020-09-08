package com.anzaiyun.controller.MainPage;

import com.anzaiyun.dao.UserDao;
import com.anzaiyun.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/Main")
public class MemberAddController {

    private Logger logger = Logger.getLogger(MemberAddController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/MemberAdd")
    public String MemberAdd(Authentication auth, Model model, HttpSession session){

//        User user = null;
        String username = "无";
        String userauth = "无";
        if (auth.getPrincipal()!=null){
            User user  = (User) auth.getPrincipal();
            username = user.getUsername() ;
            userauth = user.getAuthorities().toString();

        }

        logger.info("增加会员页面  用户：["+username+"]"+"  权限：["+userauth+"]");
        return "member-add.html";
    }

    @RequestMapping("/Add")
    public String Add(@RequestParam("email") String email,
                      @RequestParam("username") String username,
                      @RequestParam("tel") String tel,
                      Model model,  HttpSession session){
        UserDao userDao = new UserDao(username,tel,email);
        boolean result = userService.AddUser(userDao);
        if (result){
            model.addAttribute("msg","用户["+username+"]添加成功");
        }else {
            model.addAttribute("msg","用户["+username+"]添加失败");
        }


        return "member-add.html";
    }
}
