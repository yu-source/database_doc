package com.dhj.controller;

import com.dhj.core.DealWordHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/word")
    public String word() {
        try {
            dealWordHandler.initWordFile("");

        } catch (Exception e) {
            return "<h1>word文件生成错误！" + e.toString() + "</h1>";
        }
        return "<h1>word文件生成成功！</h1>";
    }

}
