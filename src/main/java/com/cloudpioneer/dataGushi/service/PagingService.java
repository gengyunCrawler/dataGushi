package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;

import java.util.List;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
public interface PagingService {
    //显示总的记录条数
    int getCountRecord() throws Exception;

    //根据当前页到结束页的查询
    List<WeChatDataEntity> findIimitPage(int newPage) throws Exception;

    //总的页数
    int getCountPage();
}
