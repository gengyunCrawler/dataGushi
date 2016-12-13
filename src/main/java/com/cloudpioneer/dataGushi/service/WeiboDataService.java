package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.util.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/9.
 */

public interface WeiboDataService
{
    void insertByList(List<WeiboDataEntity> weiboDataEntity) throws Exception;

    Page<WeiboDataEntity> findPageBycategoryId(String categoryId, int pageNo, int pageSize,int year,int month) throws Exception;

    public void gainData4DB(String username,String password) throws Exception;

    Set<Map<String,String>> dateList()throws Exception;



}
