package com.cloudpioneer.dataGushi.service.impl;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.mapper.WeChatDataEntityMapper;
import com.cloudpioneer.dataGushi.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
@Service
public class PagingServiceImpl implements PagingService{

    @Autowired
    WeChatDataEntityMapper weChatDataEntityMapper;

    private int countRecord;// 共有多少条记录
    private int countPage;// 共有多少页
    private int start;  //从数据库取数据的起始位置
    private int pageSize = 10;//每次取出数据的数量

    @Override
    public int getCountRecord() throws Exception{
        int count=0;
        try{
            count=weChatDataEntityMapper.countAll();
            this.countPage = ((count % pageSize) != 0 ? (count / pageSize + 1) : (count / pageSize));
        }catch(Exception e){

        }
        return count;
    }

    @Override
    public List<WeChatDataEntity> findIimitPage(int newPage) throws Exception {
        start = (newPage - 1) * pageSize;

        List<WeChatDataEntity> resultList = weChatDataEntityMapper.findIimitPage(start, pageSize);
        return resultList;
    }

    @Override
    public int getCountPage() {
        return countPage;
    }
}
