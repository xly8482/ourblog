package com.example.demo.dao;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserEntity;

@Component
public class UserDao
{
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * 
     * @param user
     */
    public void saveUser(UserEntity user)
    {
        mongoTemplate.save(user);
    }

    /**
     * 根据用户名查询对象
     * 
     * @param userName
     * @return
     */
    public List<UserEntity> findUserByUserName(String userName)
    {
        Query query = new Query();
        if(!StringUtils.isEmpty(userName)) {
            query.addCriteria(Criteria.where("userName").is(userName));
        }
        
        return mongoTemplate.find(query, UserEntity.class);
    }

    /**
     * 更新对象
     * 
     * @param user
     */
    public void updateUser(UserEntity user)
    {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        // 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, UserEntity.class);
        // 更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    /**
     * 删除对象
     * 
     * @param id
     */
    public void deleteUserById(Long id)
    {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, UserEntity.class);
    }
}
