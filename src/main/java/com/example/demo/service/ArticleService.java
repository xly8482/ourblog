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
    
    /**
     * 修改文章
     * 
     * @param article
     */
    public void updateArticle(ArticleEntity article);
    
    /**
     * 删除文章
     * 
     * @param article
     */
    public void deleteArticle(String id);
}
