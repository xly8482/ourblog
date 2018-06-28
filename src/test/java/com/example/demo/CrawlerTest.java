package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.cookie.ZhiHuJavaTopCrawler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerTest
{
    @Autowired
    private ZhiHuJavaTopCrawler zhiHuJavaTopCrawler;

    @Test
    public void getZhihuTopic() throws Exception
    {
        zhiHuJavaTopCrawler.getZhihuJavaTopic();
    }

    // /**
    // * @param crawlPath crawlPath is the path of the directory which maintains
    // * information of this crawler
    // * @param autoParse if autoParse is true,BreadthCrawler will auto extract
    // * links which match regex rules from pag
    // */
    // public CrawlerTest(String crawlPath, boolean autoParse)
    // {
    // super(crawlPath, autoParse);
    // /* start page */
    // this.addSeed("https://www.zhihu.com/topic/19561132/top-answers");
    //
    // /* fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml */
    //// this.addRegex("http://news.hfut.edu.cn/show-.*html");
    // /* do not fetch jpg|png|gif */
    //// this.addRegex("-.*\\.(jpg|png|gif).*");
    // /* do not fetch url contains # */
    //// this.addRegex("-.*#.*");
    //
    // setThreads(1);
    // getConf().setTopN(100);
    //
    // // setResumable(true);
    // }
    //
    // @Override
    // public void visit(Page page, CrawlDatums next)
    // {
    // String url = page.url();
    // System.out.println("******************"+url);
    //
    // /*抽取标题*/
    // String title=page.selectText("<h2 class=\"ContentItem-title\">");
    // System.out.println(title);
    //
    // /*抽取提问内容*/
    //// String question=page.getDoc().select("div[id=zh-question-detail]").text();
    //// System.out.println(question);
    //
    // /* if page is news page */
    //// if (page.matchUrl("http://news.hfut.edu.cn/show-.*html"))
    //// {
    ////
    //// /* extract title and content of news by css selector */
    //// String title = page.select("div[id=Article]>h2").first().text();
    //// String content = page.selectText("div#artibody");
    ////
    //// System.out.println("URL:\n" + url);
    //// System.out.println("title:\n" + title);
    //// System.out.println("content:\n" + content);
    ////
    //// /* If you want to add urls to crawl,add them to nextLink */
    //// /* WebCollector automatically filters links that have been fetched before */
    //// /*
    //// * If autoParse is true and the link you add to nextLinks does not match the
    //// * regex rules,the link will also been filtered.
    //// */
    //// // next.add("http://xxxxxx.com");
    //// }
    // }

}
