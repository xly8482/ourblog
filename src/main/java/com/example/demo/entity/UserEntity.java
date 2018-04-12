package com.example.demo.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class UserEntity implements Serializable
{
    private static final long serialVersionUID = -3258839839160856613L;

    private ObjectId id;

    private String userName;

    private String passWord;

    public Object getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

}
