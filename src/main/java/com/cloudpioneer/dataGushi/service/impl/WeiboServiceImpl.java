package com.cloudpioneer.dataGushi.service.impl;

import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.mapper.WeiboDataEntityMapper;
import com.cloudpioneer.dataGushi.parse.DataStoryParse;
import com.cloudpioneer.dataGushi.service.HttpService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2016/8/9.
 */
@Service
@Transactional
public class WeiboServiceImpl implements WeiboDataService
{
    @Autowired
    WeiboDataEntityMapper  weiboDataEntityMapper;
    @Override
    public void insertByList(List<WeiboDataEntity> weiboDataEntityList) throws Exception
    {
        for (WeiboDataEntity weiboDataEntity:weiboDataEntityList){
            weiboDataEntityMapper.insert(weiboDataEntity);
        }

    }

    /**
     * categoryId=="all" 即为查询全部
     * @param categoryId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Page<WeiboDataEntity> findPageBycategoryId(String categoryId, int pageNo, int pageSize,int year,int month) throws Exception
    {

        int limit = pageSize;
        int start = (pageNo-1)*pageSize;
        int totalRecord =0;
        List<WeiboDataEntity> categorysEntities=null;
       if (categoryId.equals("all")){
           totalRecord= weiboDataEntityMapper.countAll();
           categorysEntities= weiboDataEntityMapper.findPageByAll(start, limit,year,month);
       }else {

          totalRecord = weiboDataEntityMapper.countByCategoryId(categoryId,year,month);
           categorysEntities= weiboDataEntityMapper.findPageByCategory(categoryId, start, limit,year,month);

       }

        int totalPage = 1 + (totalRecord - 1) / pageSize;

        Page<WeiboDataEntity> page=new Page<>();



        page.setDatas(categorysEntities);
        page.setCurrentPage(pageNo);
        page.setPageSize(pageSize);
        page.setTotalPage(totalPage);
        page.setTotalRecord(totalRecord);

        return page;
    }

    @Override
    public void gainData4DB() throws Exception
    {


        String json=HttpService.dataStoryJSON(null,null,HttpService.DATA_WEIBO);
        List<WeiboDataEntity> weiboDataEntities=DataStoryParse.parseJson4Weibo(json);

        if (weiboDataEntities!=null&&weiboDataEntities.size()>0){
            Calendar cal=Calendar.getInstance();
            Date currentDate=cal.getTime();
            System.out.println(currentDate);
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
            Date startDate=cal.getTime();
            weiboDataEntityMapper.setDeleteFlagByMonth(startDate, currentDate,"true");
        }
        this.insertByList(weiboDataEntities);


    }

    @Override
    public Set<Map<String, String>> dateList() throws Exception
    {
        List<Date> dates=weiboDataEntityMapper.findDateList();
       Set<Map<String,String>> set=new HashSet<>();
        for (Date date:dates){
            if (date!=null){
                Map<String,String> map=new HashMap<>();
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(date);
                map.put(calendar.get(Calendar.YEAR)+"",calendar.get(Calendar.MONTH)+"");
                set.add(map);
            }

        }
        return set;
    }

}
