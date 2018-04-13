package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ArticleEntity;

public interface ArticleService
{

    /**
     * 新增文章
     * 
     * @param article
     */
    public void addArticle(ArticleEntity article);

    /**
     * 查询我的文章列表
     * 
     * @param article
     * @return
     */
    public List<ArticleEntity> listMyArticle(String userId);
}
