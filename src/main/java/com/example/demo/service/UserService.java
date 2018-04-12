package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    public List<UserEntity> listUser(String userName)
    {
        return userDao.findUserByUserName(userName);
    }
}
