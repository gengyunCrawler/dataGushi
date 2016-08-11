package com.cloudpioneer.dataGushi.service.impl;

import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.mapper.WeiboDataEntityMapper;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Page<WeiboDataEntity> findPageBycategoryId(String categoryId, int pageNo, int pageSize) throws Exception
    {

        int limit = pageSize;
        int start = (pageNo-1)*pageSize;
        int totalRecord =0;
        List<WeiboDataEntity> categorysEntities=null;
       if (categoryId.equals("all")){
           totalRecord= weiboDataEntityMapper.countAll();
           categorysEntities= weiboDataEntityMapper.findPageByAll(start, limit);
       }else {

           totalRecord = weiboDataEntityMapper.countByCategoryId(categoryId);
           categorysEntities= weiboDataEntityMapper.findPageByCategory(categoryId, start, limit);

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

}
