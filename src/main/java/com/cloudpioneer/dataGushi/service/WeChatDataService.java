package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
public interface WeChatDataService {

    void insertByList(List<WeChatDataEntity> weChatDataEntities) throws Exception;

    Page<WeChatDataEntity> findIimitPage(int year, int month, int newPage, int pageSize, String categoryId) throws Exception;

    public void gainData(String username,String password,String type)throws Exception;

    String findWxDetail(int year, int month,String wxBiz);

    void wxDetailToArticles(int year,int month);
}
