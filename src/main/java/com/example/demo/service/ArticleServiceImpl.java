package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dao.ArticleDao;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.util.ClassTransformUtil;

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

    @Override
    public void updateArticle(ArticleEntity article)
    {
        ArticleEntity oldArticle = articleDao.getById(article.getId());
        ClassTransformUtil.copy(oldArticle, article, new String[]{"id", "addtime", "userId"});
        oldArticle.setUpdatetime(new Date());
        articleDao.save(article);
    }

    @Override
    public void deleteArticle(String id)
    {
        if (!StringUtils.isEmpty(id))
        {
            articleDao.deleteById(id);
        }
    }

}
