package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public class ArticleEntity implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 6113169264584616475L;

    private ObjectId id;

    private String title; // 标题

    private String context; // 文章内容：支持富文本

    private String titleImgUrl; // 文章配图

    private String userId; // 文章用户

    private Date addtime; // 创建时间

    private Date updatetime; // 修改时间

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContext()
    {
        return context;
    }

    public void setContext(String context)
    {
        this.context = context;
    }

    public String getTitleImgUrl()
    {
        return titleImgUrl;
    }

    public void setTitleImgUrl(String titleImgUrl)
    {
        this.titleImgUrl = titleImgUrl;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Date getAddtime()
    {
        return addtime;
    }

    public void setAddtime(Date addtime)
    {
        this.addtime = addtime;
    }

    public Date getUpdatetime()
    {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime)
    {
        this.updatetime = updatetime;
    }
}
