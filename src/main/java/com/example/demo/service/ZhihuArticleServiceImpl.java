package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ZhihuArticleDao;
import com.example.demo.entity.ZhihuArticleEntity;

@Service
public class ZhihuArticleServiceImpl implements ZhihuArticleService
{
    @Autowired
    private ZhihuArticleDao zhihuArticleDao;

    @Override
    public void addArticle(ZhihuArticleEntity article)
    {
        article.setAddtime(new Date());
        article.setUpdatetime(new Date());
        zhihuArticleDao.save(article);
    }
}
