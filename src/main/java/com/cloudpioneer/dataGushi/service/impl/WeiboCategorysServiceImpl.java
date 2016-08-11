package com.cloudpioneer.dataGushi.service.impl;

import com.cloudpioneer.dataGushi.domain.WeiboCategorysEntity;
import com.cloudpioneer.dataGushi.mapper.WeiboCategorysEntityMapper;
import com.cloudpioneer.dataGushi.service.WeiboCategorysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
@Service
public class WeiboCategorysServiceImpl implements WeiboCategorysService
{
    @Autowired
    private WeiboCategorysEntityMapper categorysEntityMapper;
    @Override
    public void insertByList(List<WeiboCategorysEntity> weiboCategorysEntities) throws Exception
    {
        for (WeiboCategorysEntity categorysEntity:weiboCategorysEntities){
            categorysEntityMapper.insert(categorysEntity);
        }
    }
}
