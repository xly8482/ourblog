package com.example.demo.entity;

import java.io.Serializable;

public class ResourceEntity implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 3646069135634525698L;

    private String resourceName;

    private Long uploadTime;

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public Long getUploadTime()
    {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime)
    {
        this.uploadTime = uploadTime;
    }
}
