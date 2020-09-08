package com.anzaiyun.conf;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/Main/index").hasRole("user")
                .antMatchers("/Main/MemberList").hasRole("admin_find")
                .antMatchers("/Main/MemberAdd").hasRole("admin_add")
                .and()
                //loginPage是登录页面的解析地址
                .formLogin().loginPage("/login")
                //loginProcessingUrl是登录表单的提交地址
                            .loginProcessingUrl("/Main/index").defaultSuccessUrl("/Main/index")
                .and()
                .csrf().disable()
                //允许iframe frame加载同源的资源
                .headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("1").password(new BCryptPasswordEncoder().encode("1")).roles("user")
                .and()
                .withUser("2").password(new BCryptPasswordEncoder().encode("2")).roles("user","admin_find")
                .and()
                .withUser("3").password(new BCryptPasswordEncoder().encode("3")).roles("user","admin_find","admin_add");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/fonts/**","/images/**","/js/**","/lib/**");
    }
}
