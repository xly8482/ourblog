package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    @Autowired
    private UserService userService;

    /**
     * 根据名称查询用户列表
     * 
     * @param userName
     * @return
     */
    @GetMapping("/listUser")
    @ResponseBody
    public List<UserEntity> createPPaperFolder(String userName)
    {
        return userService.listUser(userName);
    }
}
