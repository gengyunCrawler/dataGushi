package com.cloudpioneer.dataGushi.mapper;

import com.cloudpioneer.dataGushi.domain.ArticleEntity;
import com.cloudpioneer.dataGushi.domain.WxArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tijun on 2016/11/22.
 * @author TijunWang
 */
@Repository
public interface ArticleEntityMapper {

    void insert(ArticleEntity articleEntity);

    void updateDeleteFlag(@Param("year")int year,@Param("month")int month,@Param("wxBiz")String wxBiz);

    void deleteByWxBizDate(@Param("year")int year,@Param("month")int month,@Param("wxBiz")String wxBiz);

    List<ArticleEntity> findByWxBizAndDate(@Param("year")int year, @Param("month")int month, @Param("wxBiz")String wxBiz);

    List<ArticleEntity> findByCategoryId(@Param("year")int year,@Param("month")int month,@Param("start") int start,@Param("size")int size,@Param("categoryId")String categoryId);

    List<ArticleEntity> findAllByPage(@Param("year")int year,@Param("month")int month,@Param("start") int start,@Param("size")int size);

    Integer countByCategoryId(@Param("year")int year,@Param("month") int month,@Param("categoryId") String categoryId);

    Integer countAll(@Param("year")int year,@Param("month")int month);

    List<String> findUrlByAll(@Param("year")int year,@Param("month")int month);

    List<String> findUrlByPage(@Param("year")int year,@Param("month")int month,@Param("start") int start,@Param("size") int size );
  //  List<WxArticle> findByPage("")
}
