package com.anzaiyun.mapper;

import com.anzaiyun.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    public UserDao FindById(int id);
}
