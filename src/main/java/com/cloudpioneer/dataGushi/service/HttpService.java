package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.util.FileUtil;
import com.cloudpioneer.dataGushi.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
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

    public static String dataStoryJSON(String weiboOrWeixin) throws IOException
    {
        String url1="http://service.datastory.com.cn/account/login?finalurl=http://f.datastory.com.cn";
        HttpGet disID=new HttpGet(url1);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(null).build();
        HttpResponse response = excuteHttpMethod(client);

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
        String url2="http://service.datastory.com.cn/account/loginAccount";
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
}
