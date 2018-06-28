package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class ZhihuArticleEntity implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private Date addtime; // 创建时间

    private Date updatetime; // 修改时间

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
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
