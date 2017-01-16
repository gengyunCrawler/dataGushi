package com.gy.test;

import com.cloudpioneer.dataGushi.service.WeChatDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 1.获取微信榜单所有文章数据包括文章内容逻辑是：（dev_zhengwu）
 * 将文章从wechadata的detail字段解析到并存入wx0Article 中，然后让zkCrawler 去查找wxArticle 中content为null的文章url，
 * 然后爬虫进行文章下载，并将下载后解析的文章内容更新到wxArticle 表中的content字段（已完成在zkCrawler的dev_wxArticle分支）
 * 建议：wechatdata和wxArticle 在做文章数据这部分内容时另建新表来完成这部分类容
 *2.指数计算系统含权重
 * 在dev_index_final 分支，注意看WechatDataMapper.xml 中的数据表，它比另外的表多了gwi的权重，一般夏哥那边需要此部分数据
 *3.微信榜单发布的分支dev_verify：
 * 按时间段处理得逻辑，但线上的数据规则仍然是按照每天获取一次并且是数说的默认时间段获取的表列，需1月结束后换成目前的按时间段获取的逻辑，
 * 当前分支实现按自定义时间段获取数据，但未做成自动化。
 * 4注意看mapper中的表名
 */
@ContextConfiguration("classpath:spring-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class WeixinTester {

    @Autowired
    private WeChatDataService weChatDataService;

    /**
     * 将wechatData表中的detail字段解析出微信文章并存在wxArticle 表中
     */
    @Test
    public void testDataTransfer(){
        weChatDataService.exDetailToArticles();
    }

    /**
     * 根据时间段获取数说中的微信排行榜数据
     */
    @Test
    public void testGetWxData(){
        ResourceBundle bundle = PropertyResourceBundle.getBundle("account");//accout中保存了获取数据的账号
        try {
            String startTime = "1467302400000";/**long 型的开始时间格式*/
            String endTime = "1483977600000";  /**long 型的结束时间*/
            weChatDataService.gainData(bundle.getString("username"),bundle.getString("password"),startTime,endTime,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
