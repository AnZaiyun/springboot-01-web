package com.anzaiyun.Interceptor;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler
                             ) throws Exception {

        //获取当前的session对象内保存的内容
        Enumeration<String> names = request.getSession().getAttributeNames();
        while (names.hasMoreElements()){
            String tmp = names.nextElement();
//            System.out.println(tmp);
        }
        //登录成功后，与用户相关的信息全部封装到session的SPRING_SECURITY_CONTEXT属性中，然后登录成功跳转是重定向，request参数会丢失
        //1、通过session获取SecurityContext对象
        SecurityContext context_session = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication auth = null;
        String username = null;
        if (context_session != null){
            //未登录时改session对象不存在
            auth = context_session.getAuthentication();
            username = auth.getName();
//            System.out.println("security封装后的name："+username);
        }

        //2、通过SecurityContextHolder获得当前线程上绑定的SecurityContext对象
        SecurityContext context = SecurityContextHolder.getContext();
        auth = context.getAuthentication();
        username = auth.getName();
//        System.out.println("security封装后的name(通过SecurityContextHolder获取)："+username);
        //获取用户的权限字
//        System.out.println(auth.getAuthorities().toString());

        logger.info("开始校验用户："+username);

        if (username == null || "".equals(username) || "anonymousUser".equals(username)){
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
