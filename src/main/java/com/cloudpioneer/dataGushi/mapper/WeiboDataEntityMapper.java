package com.cloudpioneer.dataGushi.mapper;

import com.cloudpioneer.dataGushi.dao.BaseDao;
import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
@Repository
public interface WeiboDataEntityMapper extends BaseDao<WeiboDataEntity>
{
    /**
     * 根据微博分类查询
     * @param categoryId 分类ID
     * @param start
     * @param limit
     * @return
     * @throws Exception
     */
    List<WeiboDataEntity> findPageByCategory(@Param("categoryId") String categoryId, @Param("start") int start, @Param("limit") int limit,@Param("year")int year,@Param("month")int month)throws Exception;

    Integer countByCategoryId(@Param("categoryId") String categoryId,@Param("year")int year,@Param("month")int month)throws Exception;

    List<WeiboDataEntity> findPageByAll(@Param("start") int start, @Param("limit") int limit,@Param("year")int year,@Param("month")int month)throws Exception;

    void deleteAll() throws Exception;

    void setDeleteFlagByMonth(@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("deleteFlag")String deleteFlag) throws Exception;

    List<Date> findDateList()throws Exception;
}
