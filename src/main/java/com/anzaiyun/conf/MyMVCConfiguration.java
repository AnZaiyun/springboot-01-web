package com.anzaiyun.conf;

import com.anzaiyun.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * addViewController是在当前类的URL路径下添加的RequestMapping
         * 比如类A RequestMapping(/111)
         * 类A的方法B RequestMapping(/222)
         * 如果方法B中跳转url：/333,在这里进行ViewController的设置，最后的跳转地址是/111/333
         * 如果想要使用根路径则需要添加redirect，使用重定向跳转
         * 这种配置方法不太友好，对付复杂带参的页面还是要单独写一个Controller文件
         */
//        registry.addViewController("/login.html").setViewName("redirect:/login");
    }

    //    定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/Main/**").
                excludePathPatterns("/css/**","/fonts/**","/images/**","/js/**","/lib.layui/**","/Main/index","/login");
    }
}
