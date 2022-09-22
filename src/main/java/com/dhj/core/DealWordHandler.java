package com.dhj.core;

import com.dhj.service.ITableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import static com.dhj.config.ConfigConstants.DATABASE_NAME;
import static com.dhj.config.ConfigConstants.PROJECT_NAME;
import static com.dhj.config.ContentConstants.*;

/**
 * @Author gmd
 * @Description word文档处理核心类
 * @Date 2022-09-21 18:19:03
 */
@Component
@RequiredArgsConstructor
public class DealWordHandler {

    private final ITableInfoService tableInfoService;

    public void initWordFile(String fileName) throws Exception {

        File file = new File("./src/main/resources/static/xml/" + System.currentTimeMillis() + PROJECT_NAME + "数据库设计文档.xml");
        FileWriter fileWriter = null;//创建文件写入对象
        BufferedWriter writer = null;//创建字符流写入对象

        fileWriter = new FileWriter(file);
        writer = new BufferedWriter(fileWriter);

        writer.write(HEADER);
        writer.newLine();
        writer.write(AUTHOR_INFO);
        writer.newLine();
        writer.write(FILE_STYLE);
        writer.newLine();

        /**
         * 设置首页标题
         */
        String indexInfo = INDEX_INFO.replace("${projectName}", PROJECT_NAME);
        writer.write(indexInfo);
        writer.newLine();

        List<Map<String, String>> tableList = tableInfoService.queryAllTableName(DATABASE_NAME);
        for (Map<String, String> ele : tableList) {

            String tableName = TABLE_NAME.replace("${tableDesc}", ele.get("tableCommit"));
            String tableName2 = tableName.replace("${tableName}", ele.get("tableName"));
            writer.write(tableName2);
            writer.newLine();

            writer.write(TABLE_START_FLAG);
            writer.newLine();

            writer.write(TABLE_HEADER);
            writer.newLine();

            List<Map<String, String>> data = tableInfoService.queryTableInfoByName(DATABASE_NAME, ele.get("tableName"));
            for (Map<String, String> fields : data) {
                String tableBody = "";
                if ("YES".equals(fields.get("isKey"))) {
                    tableBody = TABLE_BODY_IS_KEY;
                } else {
                    tableBody = TABLE_BODY_NO_KEY;
                }
                tableBody = tableBody.replace("${serialNo}", fields.get("serialNo"));
                tableBody = tableBody.replace("${fieldName}", fields.get("fieldName"));
                tableBody = tableBody.replace("${typeAndLen}", fields.get("typeAndLen"));
                tableBody = tableBody.replace("${describe}", fields.get("describeInfo"));

                writer.write(tableBody);
                writer.newLine();

            }

            writer.write(TABLE_END_FLAG);
            writer.newLine();

            writer.write(WRAP);
            writer.newLine();
            writer.write(WRAP);
            writer.newLine();

        }

        writer.write(FOOT);
        writer.newLine();


        writer.close();
        fileWriter.close();

    }


}
