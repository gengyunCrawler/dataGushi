package com.cloudpioneer.dataGushi.mapper;

import com.cloudpioneer.dataGushi.dao.BaseDao;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * Created by Tianjinjin on 2016/8/9.
 */
@Repository
public interface WeChatDataEntityMapper extends BaseDao<WeChatDataEntity> {

    List<WeChatDataEntity> findIimitPage(@Param("year") int year, @Param("month") int month,@Param("start") int start, @Param("pageSize") int pageSize) throws Exception;

    List<WeChatDataEntity> findByCategoryId(@Param("year") int year, @Param("month") int month,@Param("start") int start, @Param("pageSize") int pageSize, @Param("categoryId") String categoryId) throws Exception;

    int countByCategory(@Param("categoryId") String categoryId)throws Exception;

    void deleteAll() throws Exception;

    void updateDate(@Param("beforeDate") Date beforeDate, @Param("currentDate") Date currentDate,@Param("username")String username) throws Exception;


    WeChatDataEntity findWeChatDataEntity(@Param("year") int year, @Param("month") int month,@Param("wxBiz")String wxBiz);

    List<WeChatDataEntity> findAll(@Param("year")int year,@Param("month")int month);
}
