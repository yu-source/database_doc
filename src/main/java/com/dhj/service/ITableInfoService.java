package com.dhj.service;

import java.util.List;
import java.util.Map;

/**
 * @Author gmd
 * @Description 表信息service层
 * @Date 2022-09-22 15:42:39
 */
public interface ITableInfoService {

    /**
     * 获取指定数据库下所有的表
     */
    List<Map<String, String>> queryAllTableName(String databaseName);

    /**
     * 根据表名称获取表的属性信息
     */
    List<Map<String, String>> queryTableInfoByName(String databaseName, String tableName);

}
