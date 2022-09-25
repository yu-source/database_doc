package com.dhj.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.dhj.entity.StyleEntity;
import com.dhj.entity.WordEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author gmd
 * @Description 页面配置解析工具类
 * @Date 2022-09-25 08:47:09
 */
@Slf4j
public class PageConfigUtils {

    public static StyleEntity analysisConfig(Map<String, String[]> config) {
        StyleEntity style = new StyleEntity();

        style.setIsBold(config.get("isBold")[0]);
        style.setFilterField(config.get("filterField"));
        if (HexUtil.isHexNumber("#" + config.get("backgroundColor")[0])) {
            style.setBackgroundColor("#" + config.get("backgroundColor")[0]);
        }
        style.setFiledLen(config.get("checkList").length);

        WordEntity wordEntity = new WordEntity();
        String needField = Arrays.toString(config.get("checkList"));

        if(needField.contains("serialNo")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("serialNo")[0], "序号"));
        }
        if(needField.contains("fieldName")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("fieldName")[0], "字段名"));
        }
        if(needField.contains("type")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("type")[0], "类型"));
        }
        if(needField.contains("length")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("length")[0], "长度"));
        }
        if(needField.contains("typeAndLen")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("typeAndLen")[0], "类型(长度)"));
        }
        if(needField.contains("precision")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("precision")[0], "精度"));
        }
        if(needField.contains("numScale")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("numScale")[0], "小数位数"));
        }
        if(needField.contains("allowEmpty")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("allowEmpty")[0], "允许为空"));
        }
        if(needField.contains("mainKey")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("mainKey")[0], "主键"));
        }
        if(needField.contains("defaultVal")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("defaultVal")[0], "默认值"));
        }
        if(needField.contains("describeInfo")){
            wordEntity.setSerialNo(StrUtil.blankToDefault(config.get("describeInfo")[0], "描述"));
        }

        style.setWordEntity(wordEntity);

        log.info("------ word配置信息：背景颜色：{}，是否加粗：{}，过滤字段：{}，显示字段（{}）：{}",style.getBackgroundColor(),
                style.getIsBold(), style.getFilterField(), style.getFiledLen(), needField);
        return style;
    }

}
