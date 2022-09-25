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
 * @Description 表单word文档处理核心类
 * @Date 2022-09-21 18:19:03
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FormWordHandler {

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


        // 优雅关闭资源
        writer.close();
        fileWriter.close();
    }

}
