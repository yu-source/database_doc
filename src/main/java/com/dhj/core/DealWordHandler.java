package com.dhj.core;

import com.dhj.entity.StyleEntity;
import com.dhj.service.ITableInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import static com.dhj.config.ContentConstants.*;

/**
 * @Author gmd
 * @Description word文档处理核心类
 * @Date 2022-09-21 18:19:03
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DealWordHandler {

    private final ITableInfoService tableInfoService;

    @Value("${project.name:XXX项目}")
    private String projectName;

    @Value("${project.database-name:test}")
    private String databaseName;


    public void createWordFile(StyleEntity style, String filePathName) throws Exception {
        // 创建文件写入对象
        FileWriter fileWriter = null;
        // 创建字符流写入对象
        BufferedWriter writer = null;

        fileWriter = new FileWriter(new File(filePathName));
        writer = new BufferedWriter(fileWriter);

        // 写入文件数据
        writeWordData(writer);

        // 优雅关闭资源
        writer.close();
        fileWriter.close();
    }


    public void writeWordData(Writer writer) throws Exception {
        // 设置word头信息
        writer.write(HEADER);
        writer.write(AUTHOR_INFO);
        writer.write(FILE_STYLE);

        // 设置word首页
        writer.write(INDEX_INFO.replace("${projectName}", projectName));

        // 遍历数据表，写入表信息
        List<Map<String, String>> tableList = tableInfoService.queryAllTableName(databaseName);
        for (Map<String, String> ele : tableList) {
            // 插入数据表标题信息
            String tableName = TABLE_NAME.replace("${tableDesc}", ele.get("tableCommit"))
                    .replace("${tableName}", ele.get("tableName"));
            writer.write(tableName);

            // 插入word表开始标识及表头
            writer.write(TABLE_START_FLAG);
            writer.write(TABLE_HEADER);

            // 遍历表字段
            List<Map<String, String>> data = tableInfoService.queryTableInfoByName(databaseName, ele.get("tableName"));
            for (Map<String, String> fields : data) {
                String tableBody = "";
                if ("YES".equals(fields.get("isKey"))) {
                    tableBody = TABLE_BODY_IS_KEY;
                } else {
                    tableBody = TABLE_BODY_NO_KEY;
                }
                tableBody = tableBody.replace("${serialNo}", fields.get("serialNo"))
                        .replace("${fieldName}", fields.get("fieldName"))
                        .replace("${typeAndLen}", fields.get("typeAndLen"))
                        .replace("${describe}", fields.get("describeInfo"));
                // 写入表字段行信息
                writer.write(tableBody);
            }

            // 插入word表结束标识
            writer.write(TABLE_END_FLAG);

            // 换行，二行
            writer.write(WRAP);
            writer.write(WRAP);

            log.info("------【"+ele.get("tableCommit")+"】"+ele.get("tableName")+"生成成功");
        }

        // 设置word末尾信息
        writer.write(FOOT);
    }

}
