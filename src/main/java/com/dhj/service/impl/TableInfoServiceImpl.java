package com.dhj.service.impl;

import com.dhj.mapper.TableInfoMapper;
import com.dhj.service.ITableInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author gmd
 * @Description 表信息service实现层
 * @Date 2022-09-22 15:43:22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TableInfoServiceImpl implements ITableInfoService {

    private final TableInfoMapper tableInfoMapper;

    @Override
    public List<Map<String, String>> queryAllTableName(String databaseName) {
//        return tableInfoMapper.queryAllTableName(databaseName);
        // 使用达梦数据库
        return tableInfoMapper.queryAllTableNameByDM(databaseName);
    }

    @Override
    public List<Map<String, String>> queryTableInfoByName(String databaseName, String tableName) {
//        return tableInfoMapper.queryTableInfoByName(databaseName, tableName);
        // 使用达梦数据库
        return tableInfoMapper.queryTableInfoByNameByDM(databaseName, tableName);
    }

}
