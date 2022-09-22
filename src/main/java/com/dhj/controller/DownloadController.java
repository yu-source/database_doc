package com.dhj.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.dhj.core.DealWordHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Date;

import static com.dhj.config.ConfigConstants.*;

/**
 * @Author gmd
 * @Description word下载controller层
 * @Date 2022-09-21 17:20:09
 */
@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
public class DownloadController {

    private final DealWordHandler dealWordHandler;

    /**
     * 默认下载word到本地
     */
    @GetMapping("/word")
    public String word() {
        return word2("");
    }


    /**
     * 下载word到本地并指定名称
     */
    @GetMapping("/word2")
    public String word2(@RequestParam("fileName") String fileName) {
        if (StrUtil.isBlank(fileName)) {
            fileName = PROJECT_NAME + "数据库设计文档_" + DateUtil.format(new Date(), "HHmmss");
        }
        try {
            dealWordHandler.initWordFile(FILE_SAVA_PATH + fileName + SUFFIX);

        } catch (Exception e) {
            return "<h1>word文件生成错误！" + e.toString() + "</h1>";
        }
        return "<h1>word文件生成成功！文件名称：" + fileName + SUFFIX + "</h1>";
    }


    @GetMapping("/getWord")
    public void getWord(HttpServletResponse response) throws Exception {
        getWord2("", response);
    }


    @GetMapping("/getWord2")
    public void getWord2(@RequestParam("fileName") String fileName, HttpServletResponse response) throws Exception {
        if (StrUtil.isBlank(fileName)) {
            fileName = PROJECT_NAME + "数据库设计文档_" + DateUtil.format(new Date(), "HHmmss");
        }

        // 通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + SUFFIX);

        OutputStreamWriter utf8Writer = IoUtil.getUtf8Writer(response.getOutputStream());

        // 写入文件数据
        dealWordHandler.writeWordData(utf8Writer);

        utf8Writer.flush();
        utf8Writer.close();
    }

}
