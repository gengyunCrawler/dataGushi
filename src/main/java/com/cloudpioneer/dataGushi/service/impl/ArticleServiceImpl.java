package com.cloudpioneer.dataGushi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloudpioneer.dataGushi.domain.ArticleEntity;
import com.cloudpioneer.dataGushi.domain.WxArticle;
import com.cloudpioneer.dataGushi.mapper.ArticleEntityMapper;
import com.cloudpioneer.dataGushi.service.ArticleService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<String> findUrlByMonth(int year, int month) {
        return articleMapper.findUrlByAll(year,month);
    }

    @Override
    public Page<String> findUrlByPage(int year, int month, int pageNo, int pageSize) {
//        if (year < 1 || month < 1 || pageNo<1||pageSize<1){
//            throw new IllegalArgumentException("")
//        }

        Page<String> page = new Page<>();
        int start = (pageNo-1) * pageSize;
        int total = articleMapper.countAll(year,month);
        int totalPage = (total+1)/pageSize;
        List<String> urls = articleMapper.findUrlByPage(year,month,start,pageSize);
        page.setPageSize(pageSize);
        page.setTotalRecord(total);
        page.setCurrentPage(pageNo);
        page.setDatas(urls);
        page.setTotalPage(totalPage);
        return page;
    }

    /**
     * 下载文章原文并解析
     */
    private WxArticle singleArtcleTransform(ArticleEntity entity){
        if (entity==null){
            throw  new IllegalArgumentException("entity cannot be null");
        }
        WxArticle wxArticle = new WxArticle();
        List<String> tags = new ArrayList<>();
        if (entity.getIsOriginal()>0){
            tags.add("原创");
        }
        if (entity.getHeadLineNum()>0){
            tags.add("头条");
        }
        wxArticle.setTags(JSONObject.toJSONString(tags));
        wxArticle.setWxBiz(entity.getWxBiz());
        wxArticle.setTitle(entity.getTitle());



        return null;
    }
}
