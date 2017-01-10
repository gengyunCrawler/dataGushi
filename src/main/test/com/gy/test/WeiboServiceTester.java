package com.gy.test;


import com.cloudpioneer.dataGushi.domain.ArticleEntity;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.mapper.ArticleEntityMapper;
import com.cloudpioneer.dataGushi.mapper.WeChatDataEntityMapper;
import com.cloudpioneer.dataGushi.mapper.WeiboDataEntityMapper;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.service.WeiboCategorysService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.Html;

import java.util.*;

/**
 * Created by Administrator on 2016/8/9.
 */
@ContextConfiguration("classpath:spring-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class WeiboServiceTester
{
    @Autowired
    WeiboDataService weiboDataService;

    @Autowired
    private WeiboDataEntityMapper weiboDataEntityMapper;

    @Autowired
    WeChatDataEntityMapper weChatDataEntityMapper;

    @Autowired
    WeiboCategorysService categorysService;
    @Autowired
    ArticleEntityMapper articleEntityMapper;
    @Autowired
    WeChatDataService weChatDataService;


    @Test
    public void testGainArticles(){
        List<ArticleEntity> lists = articleEntityMapper.findByCategoryId(2016,11,0,10,"5578");
    }



    @Test
    public void testGetDat(){
        Date latestDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(latestDate);
        System.out.println("year: "+ calendar.get(Calendar.YEAR)+"---  Month: " + (calendar.get(Calendar.MONTH)+1));
    }


      @Test
      public void testSetFalse() throws Exception
      {


      }

      @Test
       public void testDate(){
           int a= 6;
          int c =a;
          a = 4;
          System.out.println(c);
       }

}
