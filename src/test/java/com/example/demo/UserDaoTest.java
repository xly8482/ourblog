package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest
{
    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() throws Exception
    {
        UserEntity user = null;

        for (int i = 0; i < 10; i++)
        {
            user = new UserEntity();
            user.setUserName("小明" + i);
            user.setPassWord("xiaominghenshuai");
            userDao.saveUser(user);
        }

    }

    @Test
    public void findUserByUserName()
    {
        List<UserEntity> user = userDao.findUserByUserName("小明0");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser()
    {
        List<UserEntity> userList = userDao.findUserByUserName("小明0");

        for (UserEntity user : userList)
        {
            user.setUserName("天空");
            user.setPassWord("fffxxxx");
            userDao.updateUser(user);
        }
    }

    // @Test
    // public void deleteUserById()
    // {
    // List<UserEntity> userList = userDao.selectAll();
    // for (UserEntity user : userList)
    // {
    // userDao.deleteUserById(user.getId());
    // }
    // }
}
