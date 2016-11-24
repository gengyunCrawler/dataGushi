package com.cloudpioneer.dataGushi.mapper;

import com.cloudpioneer.dataGushi.domain.ArticleEntity;
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
}
