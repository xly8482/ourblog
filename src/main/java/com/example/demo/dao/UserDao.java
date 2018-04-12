package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.UserEntity;

@Component
public class UserDao extends BaseDaoImpl<UserEntity>
{
    /**
     * 创建对象
     * 
     * @param user
     */
    public void saveUser(UserEntity user)
    {
        this.save(user);
    }

    /**
     * 根据用户名查询对象
     * 
     * @param userName
     * @return
     */
    public List<UserEntity> findUserByUserName(String userName)
    {
        UserEntity ue = new UserEntity();
        ue.setUserName(userName);
        return this.select(ue);
    }

    /**
     * 更新对象
     * 
     * @param user
     */
    public void updateUser(UserEntity user)
    {
        this.update(user);
    }

    /**
     * 删除对象
     * 
     * @param id
     */
    public void deleteUserById(Object id)
    {
        this.deleteById(id);
    }
}
