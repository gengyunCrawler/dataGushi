package com.cloudpioneer.dataGushi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import com.cloudpioneer.dataGushi.util.Page;
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


    @RequestMapping("wx/data/{currentPage}/{pageSize}/{categoryId}/{year}/{month}")
    public  Object weChatData(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,@PathVariable("categoryId")String categoryId,
                              @PathVariable("year")int year,@PathVariable("month")int month) throws Exception {
     if(categoryId.equals("all")){
         categoryId="";
     }
        Page<WeChatDataEntity> page= weChatDataService.findIimitPage(year,month,currentPage, pageSize, categoryId);
        return page;
    }

    @RequestMapping("wx/detail/{year}/{month}/{wxBiz}")
    public Object getDetail(@PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("wxBiz") String wxBiz, HttpSession session){
        session.setAttribute("year",year);
        session.setAttribute("month",month);
        session.setAttribute("wxBiz",wxBiz);
        //返回到页面
        ModelAndView modelAndView = new ModelAndView("detail");
        return  modelAndView;
    }

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



    /**
     *
     * @param currentPage
     * @param pageSize
     * @param categoryId categoryId=="all" 即为返回全部
     * @return
     * @throws Exception
     */

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

    @RequestMapping("data/weibo/gain")
    public String wbDataGain() throws Exception
    {
            weiboDataService.gainData4DB();
        return "success";
    }

    @RequestMapping("data/weixin/gain/{username}/{password}")
    public String wxDataGain(@PathVariable("username") String username,@PathVariable("password") String password) throws Exception
    {
        weChatDataService.gainData(username,password,null);
        return "success";
    }
}
