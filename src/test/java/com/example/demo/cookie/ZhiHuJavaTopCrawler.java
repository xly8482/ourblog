package com.example.demo.cookie;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.example.demo.entity.ZhihuArticleEntity;
import com.example.demo.service.ZhihuArticleService;

/**
 * 使用WebDriver获取知乎java专栏的精华文章
 * 
 * @author xuliangyi
 * @since 2018-06-27
 * @version 1.0
 */

@Component
public class ZhiHuJavaTopCrawler
{
    @Autowired
    private ZhihuArticleService zhihuArticleService;

    public String getZhihuJavaTopic() throws Exception
    {
        String drivePath = ResourceUtils.getFile("classpath:chromedriver.exe").getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", drivePath);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.zhihu.com/topic/19561132/top-answers");
        List<WebElement> contentEles = driver.findElements(By.className("TopicFeedItem"));

        WebElement moreBtn = null;
        String content = null;
        String title = null;
        ZhihuArticleEntity zhihuArticleEntity = null;
        int sizeIndex = 0;
        for (WebElement conEle : contentEles)
        {
            moreBtn = conEle.findElement(By.className("RichContent")).findElement(By.tagName("button"));
            // 点击打开查看更多
            moreBtn.click();

            title = conEle.findElement(By.className("ContentItem-title")).getText();
            content = conEle.findElement(By.className("RichContent")).getText();

            zhihuArticleEntity = new ZhihuArticleEntity();
            zhihuArticleEntity.setTitle(title);
            zhihuArticleEntity.setContent(content);

            zhihuArticleService.addArticle(zhihuArticleEntity);

            // 间隔时间防止频繁访问，被封
            Thread.sleep(5000);
        }

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", moreBtn);
        
        return null;
    }

}
