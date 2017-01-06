package com.cloudpioneer.dataGushi.util;

import com.cloudpioneer.dataGushi.service.WeChatDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/12/13.
 */
@Component
public class WechatSchedule {
    @Autowired
    WeChatDataService  weChatDataService;

    @Scheduled(cron = "0 15 6 ? * *")
  //  @Scheduled(cron = "2 * * * * *")
    public void gainWxData(){
        ResourceBundle bundle = PropertyResourceBundle.getBundle("account");
        try {
            String datastr = this.currentTime();
            System.out.println(datastr+"  start to gain wechat data from datastory");
          //  weChatDataService.gainData(bundle.getString("username"),bundle.getString("password"),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Scheduled(cron = "0 15 7 ? * *")
    public void parseArticle(){
        try {
          String datastr = this.currentTime();
            System.out.println(datastr+"  start to refresh weChat article");
            weChatDataService.exDetailToArticles();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    private String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = sdf.format(date);
        return datastr;
    }
}
