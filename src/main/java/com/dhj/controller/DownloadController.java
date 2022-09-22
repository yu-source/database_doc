package com.dhj.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dhj.core.DealWordHandler;
import com.dhj.entity.ExcelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

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

    @PostMapping("/dealExcel")
    public void dealExcel(@RequestParam(value = "file") MultipartFile file) {
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

            List<ExcelEntity> data = EasyExcel.read(file.getInputStream()).head(ExcelEntity.class).sheet().doReadSync();

            boolean isNum = true;
            for(int i=0; i<data.size()-1; i++){
                if("1".equals(data.get(i).getNumber()) && isNum){
                    data.add(i, new ExcelEntity());
                    isNum = false;
                }else {
                    isNum = true;
                }

                if(Objects.nonNull(data.get(i).getTableName()) && !data.get(i).getTableName().equals(data.get(i+1).getTableName())){
                    data.add(i+1, new ExcelEntity(data.get(i+1).getTableName()));
                }

            }

            // 设置excel表头样式
            WriteSheet sheet = EasyExcel.writerSheet("sheet1").head(ExcelEntity.class).sheetNo(1).build();
            // 设置excel表格样式
            ExcelWriter writer = EasyExcel.write(response.getOutputStream()).needHead(true).excelType(ExcelTypeEnum.XLSX).build();

            // 写入excel数据
            writer.write(data, sheet);
            // 通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
            response.setHeader("Content-disposition", "attachment;filename=" + new String("应急预案列表".getBytes("gb2312"), "ISO8859-1") + ".xlsx");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            writer.finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
