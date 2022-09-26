package com.dhj.controller;

import cn.hutool.core.date.DateUtil;
import com.dhj.core.DealWordHandler;
import com.dhj.entity.StyleEntity;
import com.dhj.utils.PageConfigUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.dhj.config.ConfigConstants.FILE_SAVE_PATH;
import static com.dhj.config.ConfigConstants.SUFFIX;

/**
 * @Author gmd
 * @Description 表单配置word样式controller层
 * @Date 2022-09-21 17:20:09
 */
@Controller
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormConfigController {

    private final DealWordHandler dealWordHandler;

    @Value("${project.name:XXX项目}")
    private String projectName;


    @GetMapping("/index")
    public String test() {
        return "form/index";
    }


    @ResponseBody
    @PostMapping(value = "/value")
    public String value(HttpServletRequest request, HttpServletResponse response) {
        String fileName = projectName + "数据库设计文档_" + DateUtil.format(new Date(), "HHmmss");
        try {
            StyleEntity style = PageConfigUtils.analysisConfig(request.getParameterMap());
            dealWordHandler.createWordFile(style, FILE_SAVE_PATH + fileName + SUFFIX);

        } catch (Exception e) {
            return "<h1>word文件生成错误！" + e.toString() + "</h1>";
        }
        return "<h1>word文件生成成功！文件名称：" + fileName + SUFFIX + "</h1>";
    }

}
