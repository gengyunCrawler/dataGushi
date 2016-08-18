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
        /*
        String json=HttpService.dataStoryJSON(HttpService.DATA_WEIXIN);
        List<WeChatDataEntity> list= DataStoryParse.parseWeChatJSONData(json);
        System.out.println(list);
        weChatDataService.insertByList(list);
        */
        Page<WeChatDataEntity> result = weChatDataService.findIimitPage(1, 10, null);
        for(WeChatDataEntity page : result.getDatas()){
            System.out.println(page.getLatestDate());
        }
    }

    @Test
    public void testUpdateData() throws Exception
    {
       /*
        WeChatDataEntity weChatObj=new WeChatDataEntity();
        weChatObj.setUserId("TLD");
        weChatObj.setAccountId("accountId");
        weChatObj.setCategoryId("categoryId");
        weChatObj.setCategoryType("categoryType");
        weChatObj.setGroupName("groupName");
        weChatObj.setHeadPicture("http://open.weixin.qq.com/biz/headimg?wxid=");
        weChatObj.setWxName("wxName");
        weChatObj.setLastUpdateTime("lastUpdateTime");
        weChatObj.setPayTime("payTime");
        weChatObj.setExpireTime("expireTime");
        weChatObj.setWxid("wxid");
        weChatObj.setUsername("username");

        String currentDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        weChatObj.setLatestDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currentDate));
        weChatObj.setDeleteFlag(false);
        weChatDataEntityMapper.updateDate();
        weChatDataEntityMapper.insert(weChatObj);*/
        weChatDataEntityMapper.updateDate();

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
