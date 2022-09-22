package com.dhj.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


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

}
