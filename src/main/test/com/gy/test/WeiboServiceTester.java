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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        weiboDataService.gainData4DB();
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
    }

    @Test
    public void testDataTransfer(){
        weChatDataService.wxDetailToArticles(2016,11);
    }
    @Test
    public void testGetArticle(){

        List<ArticleEntity> list = articleEntityMapper.findByWxBizAndDate(2011,6,"MjM5MjQ1NTM0MA==");
    }



//    @Test
//    public void testDataF4WeChat() throws IOException, ParseException
//    {
//        String json=HttpService.dataStoryJSON(HttpService.DATA_WEIXIN);
//       List<WeChatDataEntity> list= DataStoryParse.parseWeChatJSONData(json);
//        System.out.println(list);
//    }
//
//    @Test
//    public void testGetPropertiesFile() throws IOException
//    {
//      Properties properties= FileUtil.getResourcePropertiesByName("/account.properties");
//    }
//
//    @Test
//    public void testPage() throws Exception
//    {
//        Page<WeiboDataEntity> page=weiboDataService.findPageBycategoryId("186",0,4);
//    }
//
//    @Test
//    public void testGetCategory4weibo() throws Exception
//    {
//
//       String s= HttpService.getDatastoryCategory(HttpService.DATA_WEIBO);
//        List<WeiboCategorysEntity> categorysEntities=DataStoryParse.parseWeiboCategory(s);
//        categorysService.insertByList(categorysEntities);
//    }

      @Test
      public void testSetFalse() throws Exception
      {
          Calendar cal=Calendar.getInstance();
          Date currentDate=cal.getTime();
          System.out.println(currentDate);
          cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
          Date startDate=cal.getTime();


          weiboDataEntityMapper.setDeleteFlagByMonth(startDate, currentDate,"true");

      }



}
