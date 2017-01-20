package com.cloudpioneer.dataGushi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tijun on 2016/8/9.
 */
@RestController
@RequestMapping("/")
public class DataStoryController
{
    @Autowired
    private WeChatDataService weChatDataService;

    @Autowired
    private WeiboDataService weiboDataService;
    @RequestMapping(value = "index")
    public Object index(int current){

       ModelAndView modelAndView = new ModelAndView();
        Map<String,String> map = new HashMap<>();
        modelAndView.addObject("data","this is a test data");
        modelAndView.setViewName("test");
        return modelAndView;
    }

    /**
     * 根据微信公众号类别获取排行榜（手机版也掉此接口）
     * @param currentPage 当前页
     * @param pageSize  页大小
     * @param categoryId 公众号类别
     * @param year 年
     * @param month 月
     * @param order 根据指数指标排序如 li,ri,di,gwi，或者综合指数（composite）（线上为手动sql将di,ri,li 加权赋值到qualityNum上）
     * @throws Exception
     */
    @RequestMapping("wx/data/{currentPage}/{pageSize}/{categoryId}/{year}/{month}/{order}")
    public  Object weChatData(@PathVariable("currentPage")Integer currentPage, @PathVariable("pageSize")Integer pageSize, @PathVariable("categoryId")String categoryId,
                              @PathVariable("year")int year, @PathVariable("month")int month, @PathVariable("order")String order) throws Exception {
     if(categoryId.equals("all")){
         categoryId="";
     }
        Page<WeChatDataEntity> page= weChatDataService.findIimitPage(year,month,currentPage, pageSize, categoryId,order);
        return page;
    }

    /**
     * 将微信公众号的唯一标示符wxBiz存入session然后通过接口wx/detail/get获取详细详细
     * @param year 年
     * @param month 月
     * @param wxBiz
     * @param session
     * @return
     */
    @RequestMapping("wx/detail/{year}/{month}/{wxBiz}")
    public Object getDetail(@PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("wxBiz") String wxBiz, HttpSession session){
        session.setAttribute("year",year);
        session.setAttribute("month",month);
        session.setAttribute("wxBiz",wxBiz);
        //返回到页面
        ModelAndView modelAndView = new ModelAndView("detail");
        return  modelAndView;
    }

    /**
     * 取出微信公众号的详细详细（配合接口wx/detail/{year}/{month}/{wxBiz}使用）
     * @param session
     * @return
     */
    @RequestMapping("wx/detail/get")
    public Object detailGet(HttpSession session){
        if (session ==null){
            return "session dated";
        }
        Integer year = (Integer) session.getAttribute("year");
        Integer month = (Integer) session.getAttribute("month");
        String wxBiz = (String) session.getAttribute("wxBiz");
        if (year == null || month == null || wxBiz == null || wxBiz.equals("")){
            return "param in session error";
        }
        return  weChatDataService.findArticles(year,month,wxBiz);
    }




    @RequestMapping("weibo/data/{currentPage}/{pageSize}/{categoryId}/{year}/{month}")
    public Object weiBoData(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,@PathVariable("categoryId")String categoryId,@PathVariable("year")Integer year,@PathVariable("month")Integer month) throws Exception
    {
        Page<WeiboDataEntity> page=weiboDataService.findPageBycategoryId(categoryId, currentPage, pageSize,year,month);

        return page;
    }

    /**
     * pc 端 type="weixin" /weibo
     * 手机端：mobilewx mobilewb
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("view/{type}")
    public ModelAndView wxWeibo(@PathVariable("type")String type) throws Exception
    {
       return new ModelAndView(type);
    }

    @RequestMapping("data/weibo/gain/{username}/{password}")
    public String wbDataGain(@PathVariable  String username, @PathVariable String password) throws Exception
    {
            weiboDataService.gainData4DB(username,password);
        return "success";
    }


    @RequestMapping("data/weixin/gain/{username}/{password}")
    public String wxDataGain(@PathVariable("username") String username,@PathVariable("password") String password) throws Exception
    {
     //   weChatDataService.gainData(username,password,null);
        return "success";
    }

    /**
     * 将wechataData中的detail字段解析后存入wxArticle 表中，文章排行榜由wxArticle这个表出
     */
    @RequestMapping("data/weixin/dealArticle")
    public void dealWxArticle(){
        weChatDataService.exDetailToArticles();
    }

    /**
     * 按时间段获取数据
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return
     * @throws Exception
     */
    @RequestMapping("data/weixin/gain/All")
    public Object gainAllData(String startTime,String endTime) throws Exception {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("account");

        weChatDataService.gainData(bundle.getString("username"),bundle.getString("password"),startTime,endTime,null);
        return "success";
    }
}
