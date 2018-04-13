package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.entity.ArticleEntity;

@Service
public class ArticleServiceImpl implements ArticleService
{
    @Autowired
    private ArticleDao articleDao;

    @Override
    public void addArticle(ArticleEntity article)
    {
        article.setUserId("xly");
        article.setAddtime(new Date());
        article.setUpdatetime(new Date());
        articleDao.save(article);
    }

    @Override
    public List<ArticleEntity> listMyArticle(String userId)
    {
        return articleDao.selectAll();
    }
    
    

}
