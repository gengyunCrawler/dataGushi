package com.cloudpioneer.dataGushi.service.impl;

import com.cloudpioneer.dataGushi.domain.ArticleEntity;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.mapper.WeChatDataEntityMapper;
import com.cloudpioneer.dataGushi.parse.DataStoryParse;
import com.cloudpioneer.dataGushi.service.HttpService;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
@Service
@Transactional
public class WeChatServiceImpl implements WeChatDataService{

    @Autowired
    WeChatDataEntityMapper weChatDataEntityMapper;

    ExecutorService executorService = Executors.newFixedThreadPool(15);



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
            weChatDataEntityMapper.updateDate(beginMonth,currentDate);
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
        String json=HttpService.dataStoryJSON(username,password,HttpService.DATA_WEIXIN);
        List<WeChatDataEntity> dataEntityList=DataStoryParse.parseWeChatJSONData(json);
        CloseableHttpClient client = HttpClients.custom().build();
        HttpService.login(username,password,client);
        HttpService.ajaxEx(client);
        List<ArticleEntity> articleEntityList = null;
        //用多线程处理此处慢的问题
        for (int i=0;i<dataEntityList.size();i++){
            WeChatDataEntity entity = dataEntityList.get(i);
            if (entity!=null){
               // executorService.submit()
              String details = HttpService.wxArticlesJson(entity,client);
              //取得每一篇文章
              articleEntityList =  DataStoryParse.parseWexinArticle(details);
              entity.setDetail(details);
                dataEntityList.set(i,entity);
            }
        }
        this.insertByList(dataEntityList);
    }

  private   class ExCallable implements Callable<String>{
        private String username;
        private String password;
        private WeChatDataEntity entity;
        ExCallable(String username,String password,WeChatDataEntity entity){
            this.username = username;
            this.password = password;
            this.entity = entity;
        }

        @Override
        public String call() throws Exception {
            CloseableHttpClient client = HttpClients.custom().build();
            HttpService.login(username,password,client);
            HttpService.ajaxEx(client);
            String details = HttpService.wxArticlesJson(entity,client);
            return details;
        }
    }
}
