package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.util.Page;

import java.util.List;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
public interface WeChatDataService {

    void insertByList(List<WeChatDataEntity> weChatDataEntities) throws Exception;

    Page<WeChatDataEntity> findIimitPage(int year, int month, int newPage, int pageSize, String categoryId) throws Exception;

    public void gainData()throws Exception;
}
