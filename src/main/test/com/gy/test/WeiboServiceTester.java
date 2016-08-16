package com.gy.test;


import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.parse.DataStoryParse;
import com.cloudpioneer.dataGushi.service.HttpService;
import com.cloudpioneer.dataGushi.service.WeiboCategorysService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    WeiboCategorysService categorysService;
    @Test
    public void testDataStore() throws Exception
    {
       String json= HttpService.dataStoryJSON(HttpService.DATA_WEIBO);
        System.out.println(json);
       List<WeiboDataEntity> weiboDataEntities= DataStoryParse.parseJson4Weibo(json);
        weiboDataService.insertByList(weiboDataEntities);
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
}
