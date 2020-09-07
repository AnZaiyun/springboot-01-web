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

}
