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
 * Created by Administrator on 2016/12/21.
 */
@ContextConfiguration("classpath:spring-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class WxTester {
    @Autowired
    private WeChatDataService weChatDataService;

    /**
     * 获取微信账号
     */
    @Test
    public void getWxData() throws Exception {
        final WeChatDataService service = weChatDataService;
        service.gainData("18275261274","gengyun123",null);
        service.gainData("18285115834","zhang0524",null);
        service.gainData("2108930@qq.com","ywisajax",null);
        service.deleteNoGovType();
    }
    /**
     * 删除非政府账号
     */
    @Test
    public void deleteNoGov(){
        weChatDataService.deleteNoGovType();
    }
    @Test
    public void extoArticle(){
        weChatDataService.exDetailToArticles();
    }
}
