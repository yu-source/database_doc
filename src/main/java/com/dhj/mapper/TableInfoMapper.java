package com.dhj.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Author gmd
 * @Description 表信息mapper层
 * @Date 2022-09-22 15:42:12
 */
@Mapper
public interface TableInfoMapper {

    /**
     * 获取指定数据库下所有的表
     */
    List<Map<String, String>> queryAllTableName(@Param("databaseName") String databaseName);

    /**
     * 根据表名称获取表的属性信息
     */
    List<Map<String, String>> queryTableInfoByName(@Param("databaseName") String databaseName, @Param("tableName") String tableName);

    /**
     * 获取指定数据库下所有的表 - 达梦
     */
    List<Map<String, String>> queryAllTableNameByDM(@Param("databaseName") String databaseName);

    /**
     * 根据表名称获取表的属性信息 - 达梦
     */
    List<Map<String, String>> queryTableInfoByNameByDM(@Param("databaseName") String databaseName, @Param("tableName") String tableName);

}
