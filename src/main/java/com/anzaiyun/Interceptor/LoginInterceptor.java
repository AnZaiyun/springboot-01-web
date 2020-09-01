package com.anzaiyun.Interceptor;

import org.apache.log4j.Logger;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username = (String) request.getSession().getAttribute("username");
        logger.info("开始校验用户："+username);
        logger.info("开始校验用户："+request.getParameter("username"));

//        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        Map<String,String[]> paramMap = new HashMap<String, String[]>();
//        paramMap = defaultSavedRequest.getParameterMap();
//        String[] names =  paramMap.get("username");
//        String[] pwds =  paramMap.get("password");
//        System.out.println(names+names.toString());

        Enumeration<String> names = request.getAttributeNames();
        while (names.hasMoreElements()){
            String tmp = names.nextElement();
            System.out.println(tmp);
        }

        if (username == null || "".equals(username)){
            logger.info("登录状态已失效，请重新登录");
            request.setAttribute("errormsg","登录状态已失效，请重新登录");
            //请求转发
            response.sendRedirect("/login");

            return false;
        }else{
            return true;
        }


    }
}
