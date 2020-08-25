package com.anzaiyun.Interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username = (String) request.getSession().getAttribute("username");
        logger.info("开始校验用户："+username);
        if (username == null || "".equals(username)){
            request.setAttribute("errormsg","登录状态已失效，请重新登录");
            //请求转发
            response.sendRedirect("/login");

            return false;
        }else{
            return true;
        }

    }
}
