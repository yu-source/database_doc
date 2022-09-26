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

import static com.dhj.config.ConfigConstants.MAX_WIDTH;
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

    /**
     * word样式风格
     */
    private StyleEntity style = new StyleEntity();


    /**
     * 创建word文档
     */
    public void createWordFile(StyleEntity styleEntity, String filePathName) throws Exception {
        // 创建文件写入对象
        FileWriter fileWriter = null;
        // 创建字符流写入对象
        BufferedWriter writer = null;

        fileWriter = new FileWriter(new File(filePathName));
        writer = new BufferedWriter(fileWriter);

        // 设置全局样式配置变量
        style = styleEntity;

        // 写入文件数据
        writeWordData(writer);

        // 优雅关闭资源
        writer.close();
        fileWriter.close();
    }

    /**
     * 写入word数据
     */
    public void writeWordData(Writer writer) throws Exception {
        // 设置word头信息
        writer.write(HEADER);
        writer.write(AUTHOR_INFO);
        writer.write(FIXED_HEADER_FORMAT);

        // 设置word首页
        writer.write(FIRST_TITLE_INFO.replace("${projectName}", projectName));

        // 遍历数据表，写入表信息
        List<Map<String, String>> tableList = tableInfoService.queryAllTableName(databaseName);
        for (Map<String, String> ele : tableList) {
            // 插入数据表标题信息
            String tableName = TABLE_TITLE_STYLE.replace("${tableDesc}", ele.get("tableCommit"))
                    .replace("${tableName}", ele.get("tableName"));
            writer.write(tableName);

            // 插入word数据表
            writeWordExcel(writer, ele.get("tableName"));

            // 换行，二行
            writer.write(WRAP);
            writer.write(WRAP);

            log.info("------【"+ele.get("tableCommit")+"】"+ele.get("tableName")+"生成成功");
        }

        // 设置word末尾信息
        writer.write(FOOT);
    }


    /**
     * 写入word表格数据
     */
    private void writeWordExcel(Writer writer, String tableName) throws Exception {
        // 插入word表开始标识
        writer.write(TABLE_START_FLAG);

        // 插入word表边框样式
        writer.write(TABLE_FRAME_STYLE);

        // 插入word表设置列数开始符
        writer.write(COLUMN_NUM_START_FLAG);
        // 获取每列宽度
        Integer columnWidth = MAX_WIDTH / style.getFiledLen();
        // 设置列宽度
        writer.write(COLUMN_NUM_FORMAT.replace("${width}", columnWidth.toString()));
        // 插入word表设置列数结束符
        writer.write(COLUMN_NUM_END_FLAG);

        // 设置word表头信息
        writeWordExcelHeader(writer);



        // 遍历数据库表字段
        List<Map<String, String>> data = tableInfoService.queryTableInfoByName(databaseName, tableName);
        for (Map<String, String> fields : data) {
            String tableBody = "";
            if ("YES".equals(fields.get("isKey"))) {
             //   tableBody = TABLE_BODY_IS_KEY;
            } else {
            //    tableBody = TABLE_BODY_NO_KEY;
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
    }


    /**
     * 写入word单元格数据
     */
    private void writeWordCell(Writer writer){


    }


    /**
     * 写入word表头信息
     */
    private void writeWordExcelHeader(Writer writer) throws Exception {
        // word表格行开始符
        writer.write(ROW_START_FLAG);

        for(int i = 0; i < style.getFiledLen(); i++){
            // word表单元格开始符
            writer.write(CELL_START_FLAG);


            // word表单元格开始符
            writer.write(CELL_END_FLAG);
        }

        // word表格行结束符
        writer.write(ROW_END_FLAG);
    }



}
