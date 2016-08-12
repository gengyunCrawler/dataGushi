package com.cloudpioneer.dataGushi.service.impl;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.mapper.WeChatDataEntityMapper;
import com.cloudpioneer.dataGushi.parse.DataStoryParse;
import com.cloudpioneer.dataGushi.service.HttpService;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
@Service
@Transactional
public class WeChatServiceImpl implements WeChatDataService{

    @Autowired
    WeChatDataEntityMapper weChatDataEntityMapper;

    @Override
    public void insertByList(List<WeChatDataEntity> weChatDataEntityList) throws Exception {
        for(WeChatDataEntity weChatDataEntity:weChatDataEntityList){
            weChatDataEntityMapper.insert(weChatDataEntity);
        }
    }

    @Override
    public Page<WeChatDataEntity> findIimitPage(int newPage,int pageSize,String categoryId) throws Exception {


        int countPage;// 共有多少页
        int start;  //从数据库取数据的起始位置
        int countRecord;//根据条件查询的数据库记录数

        start = (newPage - 1) * pageSize;
        List<WeChatDataEntity> resultList;
        if(categoryId==null||categoryId==""){
            countRecord=weChatDataEntityMapper.countAll();
            resultList = weChatDataEntityMapper.findIimitPage(start, pageSize);
        }
        else{
            countRecord=weChatDataEntityMapper.countByCategory(categoryId);
            resultList=weChatDataEntityMapper.findByCategoryId(start, pageSize,categoryId);
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
    public void gainData() throws Exception
    {
        weChatDataEntityMapper.deleteAll();

        String json=HttpService.dataStoryJSON(HttpService.DATA_WEIXIN);
        List<WeChatDataEntity> dataEntityList=DataStoryParse.parseWeChatJSONData(json);
        this.insertByList(dataEntityList);
    }
}
