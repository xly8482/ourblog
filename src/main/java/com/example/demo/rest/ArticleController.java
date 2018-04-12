package com.example.demo.rest;

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
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String createPPaperFolder(ArticleEntity article)
    {
        articleService.addArticle(article);
        return null;
    }
}
