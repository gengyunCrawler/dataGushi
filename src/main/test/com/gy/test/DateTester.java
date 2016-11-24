package com.gy.test;

import com.cloudpioneer.dataGushi.service.HttpService;
import com.cloudpioneer.dataGushi.util.HttpUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/18.
 */
public class DateTester
{
    @Test
    public void testDate(){

      Calendar cal=Calendar.getInstance();
      cal.getTime();
       cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 0);
        Date startDate=cal.getTime();
        System.out.println(startDate);
        //Date startDate=Calendar.getInstance().set(Calendar);
    }

    @Test
    public void testMapper(){}

    @Test
    public void testConnectHDFS() throws IOException {


    }
    @Test
    public void testGetDetailPage() throws IOException {
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(null).build();
        HttpResponse response =HttpService.login("18275261274","gengyun123",client);

        String url = "http://social.datastory.com.cn/detail/getDetail";

//        String urlCategoryWexin="http://social.datastory.com.cn/list/getCategoryList";
//        HttpGet weixinGet=HttpUtil.getMethd(urlCategoryWexin);
//        response=client.execute(weixinGet);
           String account = "http://social.datastory.com.cn/account/ajax/accountInfo?random="+Math.random();
        HttpGet get = HttpUtil.getMethd(account);
        client.execute(get);

        Map<String,String> param = new HashMap<>();
        param.put("wxBiz","MjM5MjQ1NTM0MA==");
        param.put("type","30");
       HttpPost post = HttpUtil.postForm(url,param);
        post.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
       response = client.execute(post);
      String s =  EntityUtils.toString(response.getEntity());
        System.out.println(s);

    }
    @Test
    public void testGenerateNum(){
        double s= Math.random();
        System.out.println(s);
    }

    public void testRegx(){

    }

}
