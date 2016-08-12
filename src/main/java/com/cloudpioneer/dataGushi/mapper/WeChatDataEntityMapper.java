package com.cloudpioneer.dataGushi.mapper;

import com.cloudpioneer.dataGushi.dao.BaseDao;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Tianjinjin on 2016/8/9.
 */
@Repository
public interface WeChatDataEntityMapper extends BaseDao<WeChatDataEntity> {

    List<WeChatDataEntity> findIimitPage(@Param("start") int start, @Param("pageSize") int pageSize) throws Exception;

    List<WeChatDataEntity> findByCategoryId(@Param("start") int start, @Param("pageSize") int pageSize, @Param("categoryId") String categoryId) throws Exception;

    int countByCategory(@Param("categoryId") String categoryId)throws Exception;

    void deleteAll() throws Exception;
}
