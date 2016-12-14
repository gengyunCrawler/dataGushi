package com.cloudpioneer.dataGushi.service;

import com.cloudpioneer.dataGushi.domain.ArticleEntity;
import com.cloudpioneer.dataGushi.util.Page;

import java.util.List;

/**
 * Created by Tijun on 2016/11/23.
 * @author TijunWang
 */
public interface ArticleService {
    void insert(ArticleEntity entity);
    void updateDeleteFlag(int year,int month,String zxBiz);

    Page<ArticleEntity> findAllByCategoryId(Integer year,Integer month,String categoryId,Integer pageNo,Integer size);

    Page<ArticleEntity> findAll(Integer year, Integer month, Integer pageNo, Integer size);

    void transformArticlesToContents();

    List<String> findUrlByMonth(int year,int month);

    Page<String> findUrlByPage(int year,int month,int pageNo,int pageSize);

}
