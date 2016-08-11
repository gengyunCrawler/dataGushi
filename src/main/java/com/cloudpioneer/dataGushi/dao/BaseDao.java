package com.cloudpioneer.dataGushi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用Dao层接口
 * 数据校验尽量放在前端或者service层
 * 建立实体关系映射
 * @param <T>
 * @author
 * @date 2015-11-16 11:34:44 中国标准时间
 */

public interface BaseDao<T> {

    Integer insert(T model) throws Exception;

    Integer delete(@Param("id") Object id) throws Exception;

    Integer update(T model) throws Exception;

    Integer countAll() throws Exception;


    T findById(@Param("id") Object id) throws Exception;

    List<T> findAll(@Param("order") String order) throws Exception;

    List<T> findByPage(@Param("order") String order, @Param("limit") int limit, @Param("start") int start) throws Exception;

    Integer deleteWhere(@Param("where") String where) throws Exception ;

    Integer deleteByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value) throws Exception;

    Integer countWhere(@Param("where") String where) throws Exception;

    Integer countByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value) throws Exception ;

    List<T> findWhere(@Param("order") String order, @Param("where") String where) throws Exception ;

    List<T> findWhereByPage(@Param("order") String order, @Param("where") String where, @Param("limit") int limit, @Param("start") int start) throws Exception;

    List<T> findByPropertyName(@Param("order") String order, @Param("propertyName") String propertyName, @Param("value") Object value) throws Exception;
}