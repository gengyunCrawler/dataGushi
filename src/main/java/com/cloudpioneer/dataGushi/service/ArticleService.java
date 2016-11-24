package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.ArticleEntity;

/**
 * Created by Tijun on 2016/11/23.
 * @author TijunWang
 */
public interface ArticleService {
    void insert(ArticleEntity entity);
    void updateDeleteFlag(int year,int month,String zxBiz);
}
