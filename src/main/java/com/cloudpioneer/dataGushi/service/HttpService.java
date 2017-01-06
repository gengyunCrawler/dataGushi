package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.util.FileUtil;
import com.cloudpioneer.dataGushi.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/8/9.
 */
public class HttpService
{
    public static final String DATA_WEIBO="WEIBO";
    public static final String DATA_WEIXIN="WEIXIN";

    private static String url2="http://service.datastory.com.cn/account/loginAccount";



    public static String dataStoryJSON(String username,String password,String weiboOrWeixin) throws IOException, ParseException {
        String url1="http://service.datastory.com.cn/account/login?finalurl=http://f.datastory.com.cn";
        HttpGet disID=new HttpGet(url1);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(null).build();
        HttpResponse response = login(username,password,client);

        //微博排行
        String weiboUrl="http://f.datastory.com.cn/list/getRivalUser?random="+Math.random();
        HttpGet weiboGet=new HttpGet(weiboUrl);

        //微信排行
        String weixinUrl="http://social.datastory.com.cn/list/getWxAccountList?tag=0&random="+Math.random();
        HttpGet weixinGet=new HttpGet(weixinUrl);

        if(weiboOrWeixin.equals(DATA_WEIBO)){
            response=client.execute(weiboGet);
        }
        if (weiboOrWeixin.equals(DATA_WEIXIN)){
            response=client.execute(weixinGet);
        }

       String data= EntityUtils.toString(response.getEntity());
        client.close();
        return data;
    }

    /**
     * 执行登陆操作，因为client需要保存cookie，因此传递client实例
     * @param client
     * @return
     * @throws IOException
     */
    private static HttpResponse excuteHttpMethod(CloseableHttpClient client) throws IOException
    {
        //执行登陆操作

        Map<String,String> param=new HashMap<String, String>();

        Properties properties= FileUtil.getResourcePropertiesByName("/account.properties");
        param.put("emailOrPhone",properties.get("emailOrPhone").toString());
        param.put("pwd",properties.get("pwd").toString());

        HttpPost post= HttpUtil.postForm(url2, param);
        HttpResponse response=client.execute(post);
        //
        String url3="http://f.datastory.com.cn/";
        HttpGet get1=new HttpGet(url3);
        response=client.execute(get1);
        return response;
    }

    /**
     * 得到微信或者微博的分类
     * @param weiboOrWeixin
     * @return
     * @throws IOException
     */
    public static String getDatastoryCategory(String weiboOrWeixin) throws IOException
    {

        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(null).build();

        excuteHttpMethod(client);

        String  urlCategoryWeibo="http://f.datastory.com.cn/category/list";
        String urlCategoryWexin="http://social.datastory.com.cn/list/getCategoryList";

        HttpGet weixinGet=HttpUtil.getMethd(urlCategoryWexin);
        HttpGet weiboGet=HttpUtil.getMethd(urlCategoryWeibo);
        HttpResponse response=null;
        if(HttpService.DATA_WEIBO.equals(weiboOrWeixin)){
             response=client.execute(weiboGet);
        }else if (HttpService.DATA_WEIXIN.equals(weiboOrWeixin)){
             response=client.execute(weixinGet);
        }
        return EntityUtils.toString(response.getEntity());

    }

    /**
     * 登陆账号
     * @param username
     * @param password
     * @param client
     * @return
     * @throws IOException
     */
    public static HttpResponse login(String username,String password,CloseableHttpClient client) throws IOException {
        Map<String,String> param = new HashMap<>();
        param.put("emailOrPhone",username);
        param.put("pwd",password);
        HttpPost post= HttpUtil.postForm(url2, param);
        HttpResponse response=client.execute(post);
        String url3="http://f.datastory.com.cn/";
        HttpGet get1=new HttpGet(url3);
        response=client.execute(get1);
        return response;
    }
    public static void ajaxEx(CloseableHttpClient client) throws IOException {
        String ajaxAccountUrl = "http://social.datastory.com.cn/account/ajax/accountInfo?random="+Math.random();
        HttpGet get = HttpUtil.getMethd(ajaxAccountUrl);
        client.execute(get);
    }
    /**
     * 获取微信号详情页数据
     * @param entity
     * @param client
     * @return
     * @throws IOException
     */

    public static String wxArticlesJson(WeChatDataEntity entity,CloseableHttpClient client) throws IOException {

        String detailUrl = "http://social.datastory.com.cn/detail/getDetail";
        Map<String,String> param = new HashMap<>();
        param.put("wxBiz",entity.getWxBiz());
        param.put("type","30");
        HttpPost post = HttpUtil.postForm(detailUrl,param);
        post.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        HttpResponse  response = client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 根据时间段来获微信公众号的详细时间
     * 这几个方法应该被从构
     * @param wxBiz
     * @param client
     * @return
     * @throws IOException
     */
    public static String getDetailCustom(String wxBiz, String startTime,String endTime,CloseableHttpClient client) throws IOException {
        String detailUrl = "http://social.datastory.com.cn/detail/getDetailCustom";
        Map<String,String> param = new HashMap<>();
        param.put("wxBiz",wxBiz);
        param.put("type","30");
        param.put("startTime","1480953600000");
        param.put("endTime","1483545600000");
        HttpPost post = HttpUtil.postForm(detailUrl,param);
        post.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        HttpResponse  response = client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }



}
