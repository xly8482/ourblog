package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/list")
    @ResponseBody
    public List<ArticleEntity> listArticle(String userId)
    {
        List<ArticleEntity> rsList = articleService.listMyArticle(userId);
        return rsList;
    }

    /**
     * 修改博客文章
     * 
     * @param article
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public ArticleEntity updateArticle(ArticleEntity article)
    {
        articleService.updateArticle(article);
        return article;
    }

    /**
     * 删除博客文章
     * 
     * @param article
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public int deleteArticle(@PathVariable String id)
    {
        int returnCode = 0;
        try
        {
            articleService.deleteArticle(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            returnCode = 1;
        }

        return returnCode;
    }
}
