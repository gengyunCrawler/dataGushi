package com.cloudpioneer.dataGushi.service.impl;

import com.cloudpioneer.dataGushi.domain.ArticleEntity;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.domain.WxArticle;
import com.cloudpioneer.dataGushi.mapper.ArticleEntityMapper;
import com.cloudpioneer.dataGushi.service.ArticleService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleEntityMapper articleMapper;

    @Override
    public void insert(ArticleEntity entity) {

    }

    @Override
    public void updateDeleteFlag(int year, int month, String zxBiz) {

    }

    @Override
    public Page<ArticleEntity> findAllByCategoryId(Integer year, Integer month, String categoryId, Integer pageNo, Integer size) {
        Page<ArticleEntity> page = new Page<>();
        int pstart = (pageNo-1)*size;
        List<ArticleEntity> datas = articleMapper.findByCategoryId(year,month,pstart,size,categoryId);
        Integer totalRecourd = articleMapper.countByCategoryId(year,month,categoryId);
        int totalPage = (totalRecourd %size) != 0 ? (totalRecourd / size + 1) : (totalRecourd / size);
        page.setDatas(datas);
        page.setCurrentPage(pageNo);
        page.setTotalPage(totalPage);
        page.setPageSize(size);
        page.setTotalRecord(totalRecourd);
        return page;
    }

    @Override
    public Page<ArticleEntity> findAll(Integer year, Integer month, Integer pageNo, Integer size) {
        Page<ArticleEntity> page = new Page<>();
        int pstart = (pageNo-1)*size;
        List<ArticleEntity> datas = articleMapper.findAllByPage(year,month,pstart,size);
        Integer totalRecourd = articleMapper.countAll(year,month);
        int totalPage = (totalRecourd %size) != 0 ? (totalRecourd / size + 1) : (totalRecourd / size);
        page.setDatas(datas);
        page.setCurrentPage(pageNo);
        page.setTotalPage(totalPage);
        page.setPageSize(size);
        page.setTotalRecord(totalRecourd);
        return page;
    }

    @Override
    public void transformArticlesToContents() {
        /**
         * 1.找出未下载的文章，根据文章title来判断文章是否相同（数据库中完成）
         * 2.下载文章
         * 3.解析文章
         * 4.存储文章
         */

    }
//    private WxArticle singleArtcleTransform(WeChatDataEntity entity){
//
//    }
}
