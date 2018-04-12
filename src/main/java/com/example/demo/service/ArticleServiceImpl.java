package com.example.demo.service;

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
        articleDao.save(article);
    }

}
