package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ArticleEntity;
import com.example.demo.service.ArticleService;

@RestController
@RequestMapping("/api/article")
public class ArticleController
{

    @Autowired
    private ArticleService articleService;

    /**
     * 新增博客文章
     * 
     * @param article
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ArticleEntity addArticle(ArticleEntity article)
    {
        articleService.addArticle(article);
        return article;
    }

    /**
     * 查询博客文章
     * 
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public List<ArticleEntity> listArticle(String userId)
    {
        return articleService.listMyArticle(userId);
    }
}
