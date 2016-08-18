package com.gy.test;


import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.mapper.WeChatDataEntityMapper;
import com.cloudpioneer.dataGushi.parse.DataStoryParse;
import com.cloudpioneer.dataGushi.service.HttpService;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.service.WeiboCategorysService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
@ContextConfiguration("classpath:spring-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class WeiboServiceTester
{
    @Autowired
    WeChatDataService weChatDataService;

    @Autowired
    WeChatDataEntityMapper weChatDataEntityMapper;

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

    @Test
   public void testDataF4WeChat() throws Exception
    {

        String json=HttpService.dataStoryJSON(HttpService.DATA_WEIXIN);
        List<WeChatDataEntity> list= DataStoryParse.parseWeChatJSONData(json);
        System.out.println(list);
        weChatDataService.insertByList(list);
        /*
        Page<WeChatDataEntity> result = weChatDataService.findIimitPage(1, 10, null);
        for(WeChatDataEntity page : result.getDatas()){
            System.out.println(page.getLatestDate());
        }
        */
    }

    @Test
    public void testData() throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND,0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date sdate = calendar.getTime();
        //将当前月加1；
        calendar.add(Calendar.MONTH, 1);
        //在当前月的下一月基础上减去1毫秒
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月最后一天
        Date edate = calendar.getTime();

        System.out.println("The first day of month=="+sdate.toString());
        System.out.println("first day:"+sdate.getTime());
        System.out.println("The last day of month=="+edate.toString());
        System.out.println("last day:"+edate.getTime());
    }
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
