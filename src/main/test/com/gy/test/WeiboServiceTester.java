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
    public void testDataStore() throws Exception
    {
        weiboDataService.gainData4DB("","");
    }

    @Test
    public void testSearchByDate() throws Exception
    {
        Calendar calendar=Calendar.getInstance();
     int list= weiboDataEntityMapper.countByCategoryId("231", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1);
    }

    @Test
    public void testDateSet() throws Exception
    {
      Set<Map<String,String>> set= weiboDataService.dateList();
        System.out.println(set);
    }

    @Test
    public void testFindByWxBiz(){
     // String str=  weChatDataService.findWxDetail(2016,11,"MjM5ODI2MzgzMA==");
     //   System.out.println(str);
        List<String> list =null;
        for (String s:list){
            System.out.println("tttt");
        }
    }

    @Test
    public void testDownloader(){
        HttpClientDownloader downloader = new HttpClientDownloader();
        Html html = downloader.download("http://mp.weixin.qq.com/s?__biz=MjM5NDUwMzY2MA==&mid=2650410704&idx=8&sn=82a256e14f6add8616a22f60746e8701");
        System.out.println(html.xpath("//div[@id='js_content']"));
    }
    @Test
    public void testInsertArticle(){
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setContent("dfdfdfd");
        articleEntityMapper.insert(articleEntity);
    }
    @Test
    public void testDataTransfer(){
        weChatDataService.exDetailToArticles();
    }
    @Test
    public void testGetArticle(){

        List<ArticleEntity> list = articleEntityMapper.findByWxBizAndDate(2011,6,"MjM5MjQ1NTM0MA==");
    }

    @Test
    public void testGainArticles(){
        List<ArticleEntity> lists = articleEntityMapper.findByCategoryId(2016,11,0,10,"5578");
    }

    @Test
    public void testGetWxData(){
        ResourceBundle bundle = PropertyResourceBundle.getBundle("account");
        try {
          //  String datastr = this.currentTime();
           // System.out.println(datastr+"  start to gain wechat data from datastory");
            weChatDataService.gainData(bundle.getString("username"),bundle.getString("password"),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
