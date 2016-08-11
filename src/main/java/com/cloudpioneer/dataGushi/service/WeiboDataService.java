package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.util.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */

public interface WeiboDataService
{
    void insertByList(List<WeiboDataEntity> weiboDataEntity) throws Exception;

    Page<WeiboDataEntity> findPageBycategoryId(String categoryId, int pageNo, int pageSize) throws Exception;
}
