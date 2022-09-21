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

    @GetMapping("/hello")
    public String hello() {
        dealWordHandler.initWordFile();
        return "hello";
    }

}
