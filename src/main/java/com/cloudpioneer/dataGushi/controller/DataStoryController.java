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

        WeChatDataEntity entity =   weChatDataService.findWxDetail(year,month,wxBiz);
        String detail = entity.getDetail();
        JSONObject map = new JSONObject();
        JSONObject detailObj = JSON.parseObject(detail);
        JSONObject items = detailObj.getJSONObject("items");
        JSONArray articleNumPerHour = items.getJSONObject("publishtrend").getJSONArray("articleNumPerHour");
        map.put("articleNumPerHour",articleNumPerHour);
        JSONObject userInfo = items.getJSONObject("userInfo");
        userInfo.put("descrition",items.getString("descrition"));
        userInfo.put("headPicture",entity.getHeadPicture());
        map.put("userInfo",userInfo);
        JSONArray articles = items.getJSONArray("articles");
        map.put("articles",articles);
        map.put("articleStatistics",dealStatistic(statisticsArticles(articles)));
        return  map;
    }

    private Map<Date,Map<String,Integer>>  statisticsArticles(JSONArray articles){
        if (articles==null||articles.size()==0){
            return null;
        }
        Map<Date,Map<String,Integer>> statistics = new HashMap<>();
        for (int i = 0;i<articles.size();i++){
            JSONObject article = articles.getJSONObject(i);

            String dateStr = article.getString("date");
            String day=dateStr.split(" ")[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date=null;
            try {
                 date = sdf.parse(day);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Integer readNum = article.getInteger("readNum");
            Integer likeNum = article.getInteger("likeNum");
            Integer articleNum = 1;

            Map<String,Integer> articleStatistic = statistics.get(date);

            if (articleStatistic!=null){
               Integer oldReadNum = articleStatistic.get("readNum");
               Integer oldLikeNum = articleStatistic.get("likeNum");
               Integer oldArticleNum = articleStatistic.get("articleNum");
               articleStatistic.put("readNum",readNum+oldReadNum);
               articleStatistic.put("likeNum",likeNum+oldLikeNum);
               articleStatistic.put("articleNum",articleNum+oldArticleNum);
           }else {
               articleStatistic = new HashMap<>();
               articleStatistic.put("readNum",readNum);
               articleStatistic.put("likeNum",likeNum);
               articleStatistic.put("articleNum",articleNum);

           }

            statistics.put(date,articleStatistic);

        }

        return statistics;
    }
    private Map<String,Object> dealStatistic(Map<Date,Map<String,Integer>> statistic){
        List<String> days=new ArrayList<>();
        List<Integer> readNums = new ArrayList<>();
        List<Integer> likeNums = new ArrayList<>();
        List<Integer> articleNums = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

       Set<Date> set =statistic.keySet();
        List<Date> dateList= new ArrayList<>(set);
        Collections.sort(dateList);

        for (Date date:dateList){
            sdf.format(date);
            Map<String,Integer> statisticMap= statistic.get(date);
            days.add(sdf.format(date));
            readNums.add(statisticMap.get("readNum"));
            articleNums.add(statisticMap.get("articleNum"));
            likeNums.add(statisticMap.get("likeNum"));
        }
        Map<String,Object> statisMap = new HashMap<>();
        statisMap.put("days",days);
        statisMap.put("readNum",readNums);
        statisMap.put("likeNum",likeNums);
        statisMap.put("articleNum",articleNums);
        return statisMap;
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
