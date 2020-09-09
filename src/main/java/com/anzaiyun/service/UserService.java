package com.anzaiyun.service;

import com.anzaiyun.dao.UserDao;
import com.anzaiyun.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public UserDao FindById(int id){
        return userMapper.FindById(id);
    }

    public UserDao FindByName(String vc_name){
        return userMapper.FindByName(vc_name);
    }

    public UserDao[] FindUser(){
        return userMapper.FindUser();
    }


    public Boolean AddUser(UserDao userDao){
        if (userMapper.FindByName(userDao.getVc_name()) != null)
            return false;

        userMapper.InsertUser(userDao);
        return true;
    }

}
