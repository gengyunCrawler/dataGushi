package com.cloudpioneer.dataGushi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudpioneer.dataGushi.domain.ArticleEntity;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.mapper.ArticleEntityMapper;
import com.cloudpioneer.dataGushi.mapper.WeChatDataEntityMapper;
import com.cloudpioneer.dataGushi.parse.DataStoryParse;
import com.cloudpioneer.dataGushi.service.HttpService;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
@Service
@Transactional
public class WeChatServiceImpl implements WeChatDataService{

    @Autowired
    WeChatDataEntityMapper weChatDataEntityMapper;

    private String username = "";

    private  String password = "";

    private ExecutorService executorService = Executors.newFixedThreadPool(15);

    private final BlockingQueue<CloseableHttpClient> clientBlockingQueue = new LinkedBlockingQueue<>();
    @Autowired
    private ArticleEntityMapper articleEntityMapper;




    @Override
    public void insertByList(List<WeChatDataEntity> weChatDataEntityList) throws Exception {
        /*Date currentDate=weChatDataEntityList.get(1).getLatestDate();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String tempDate=dateFormat.format(currentDate);
        Date beginMonth= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tempDate);
        weChatDataEntityMapper.updateDate(beginMonth,currentDate);*/
        if(weChatDataEntityList.size()!=0){
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
            Date beginMonth = calendar.getTime();
            //获得插入数据时的时间
            Date currentDate = weChatDataEntityList.get(0).getLatestDate();
            weChatDataEntityMapper.updateDate(beginMonth,currentDate,username);
            for(WeChatDataEntity weChatDataEntity:weChatDataEntityList){
                weChatDataEntityMapper.insert(weChatDataEntity);
            }
        }
    }

    @Override
    public Page<WeChatDataEntity> findIimitPage(int year, int month, int newPage,int pageSize,String categoryId) throws Exception {


        int countPage;// 共有多少页
        int start;  //从数据库取数据的起始位置
        int countRecord;//根据条件查询的数据库记录数

        start = (newPage - 1) * pageSize;
        List<WeChatDataEntity> resultList;
        if(categoryId==null||categoryId==""){
            countRecord=weChatDataEntityMapper.countAll();
            resultList = weChatDataEntityMapper.findIimitPage(year, month, start, pageSize);
        }
        else{
            countRecord=weChatDataEntityMapper.countByCategory(categoryId);
            resultList=weChatDataEntityMapper.findByCategoryId(year, month,start, pageSize,categoryId);
        }
        countPage = ((countRecord % pageSize) != 0 ? (countRecord / pageSize + 1) : (countRecord / pageSize));

        Page<WeChatDataEntity> resultPage=new Page<>();

        resultPage.setCurrentPage(newPage);
        resultPage.setPageSize(pageSize);
        resultPage.setTotalPage(countPage);
        resultPage.setDatas(resultList);


        return resultPage;
    }

    @Override
    public void gainData(String username,String password,String type) throws Exception
    {
        this.username = username;
        this.password = password;
        final String json=HttpService.dataStoryJSON(username,password,HttpService.DATA_WEIXIN);
        threadSleep2Sec();
        List<WeChatDataEntity> dataEntityList=DataStoryParse.parseWeChatJSONData(json);

        List<ArticleEntity> articleEntityList = null;
        if (clientBlockingQueue.size() == 0){
            for (int i=0;i<10;i++){
                clientBlockingQueue.put(prepareClient());

            }
        }
        //用多线程处理此处慢的问题
        List<Future<WeChatDataEntity>> futures = new ArrayList<>();
        for (int i=0;i<dataEntityList.size();i++){
            WeChatDataEntity entity = dataEntityList.get(i);
            if (entity!=null){
                CloseableHttpClient client1 = clientBlockingQueue.take();
                ExCallable callable = new ExCallable(entity,client1);
                Future<WeChatDataEntity>  future = executorService.submit(callable);
                futures.add(future);

            }
        }
        boolean isFinished= false;
        while (isFinished){
        int i = 0;
            this.threadSleep2Sec();
        for (Future future:futures){
            if (future.isDone()){//when the thread throw a exception ,this may result in a serious problem
                i++;

              //  futures.remove(future.get());
            }
        }
        if (i==futures.size()){
            isFinished=true;
            executorService.shutdown();
        }
        }
        dataEntityList = new ArrayList<>();
        for (Future<WeChatDataEntity> future:futures){
            WeChatDataEntity entity = future.get();
            entity.setAccount(username);

            dataEntityList.add(entity);
        }
        this.insertByList(dataEntityList);
    }

    /**
     * 查询一个具体的文章
     * @param year
     * @param month
     * @param wxBiz
     * @return
     */
    @Override
    public WeChatDataEntity findWxDetail(int year, int month, String wxBiz) {
        return weChatDataEntityMapper.findWeChatDataEntity(year,month,wxBiz);
    }

    @Override
    public JSONObject findArticles(int year, int month, String wxBiz) {
        WeChatDataEntity entity =   this.findWxDetail(year,month,wxBiz);
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
        return map;
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


    @Override
    public void wxDetailToArticles(int year, int month) {
        List<WeChatDataEntity> entities = weChatDataEntityMapper.findAll(year,month);
        //before here deleteAll articles in table article"👇"
        for (WeChatDataEntity entity : entities){
             List<ArticleEntity> articles = this.parseDetail(entity);
             this.batchAarticles(articles);
        }

    }

    /**
     * 将详细信息转换为具体的文章列表
     * @param entity
     * @return
     */
    public List<ArticleEntity> parseDetail(WeChatDataEntity entity)  {
        List<ArticleEntity> articles= new ArrayList<>();
        if (entity == null||entity.getDetail()==null||entity.getDetail().equals("")){
            throw  new RuntimeException("WechatDataEntity can not be null or detail cannot be null");
        }
        JSONArray articleArr = JSONObject.parseObject(entity.getDetail()).getJSONObject("items").getJSONArray("articles");
        if (articleArr!=null){

            for (int i=0;i<articleArr.size();i++){
                JSONObject obj = articleArr.getJSONObject(i);
                ArticleEntity articleEntity = new ArticleEntity();
                articleEntity.setOpenid(obj.getString("openid"));
                articleEntity.setWxBiz(entity.getWxBiz());
                articleEntity.setGroupName(obj.getString("groupName"));

                articleEntity.setTitle(JSONObject.toJSONString(obj.get("title")));
                articleEntity.setUrl(obj.getString("url"));
                articleEntity.setDate(obj.getDate("date"));
                articleEntity.setPublishTime(obj.getDate("publishTime"));
                articleEntity.setAuthor(obj.getString("author"));
                articleEntity.setReadNum(obj.getInteger("readNum"));
                articleEntity.setLikeNum(obj.getInteger("likeNum"));
                articleEntity.setIsOriginal(obj.getInteger("isOriginal"));
                articleEntity.setHeadLineNum(obj.getInteger("headLineNum"));
                articleEntity.setArticleId(obj.getString("articleId"));
                articleEntity.setWxName(entity.getWxName());
                articleEntity.setWxHeadPicture(entity.getHeadPicture());
                articleEntity.setCategoryId(entity.getCategoryId());
                articleEntity.setCategoryType(entity.getCategoryType());
                articles.add(articleEntity);
            }
        }
        return articles;
    }

    private void batchAarticles(List<ArticleEntity> articles){
     for (ArticleEntity entity :articles){
         System.out.println(entity.getTitle());
             articleEntityMapper.insert(entity);

     }
    }



    /**
     *
     * @return
     * @throws IOException
     */
    private CloseableHttpClient prepareClient() throws IOException {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpService.login(username,password,client);
        HttpService.ajaxEx(client);
        return  client;
    }

    /**
     * 用于执行一个下载
     */
    private   class ExCallable implements Callable<WeChatDataEntity>{

        private WeChatDataEntity entity;
        private CloseableHttpClient client;
        ExCallable(WeChatDataEntity entity,CloseableHttpClient client){
            this.client = client;
            this.entity = entity;
        }

        @Override
        public WeChatDataEntity call() throws Exception {

            String details = HttpService.wxArticlesJson(entity,client);
            entity.setDetail(details);
            clientBlockingQueue.put(client);
            return entity;
        }


    }
    private void threadSleep2Sec(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
