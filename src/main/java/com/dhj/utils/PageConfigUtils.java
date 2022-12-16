package com.dhj.utils;

import cn.hutool.core.util.HexUtil;
import com.dhj.entity.StyleEntity;
import com.dhj.entity.WordField;

import java.util.*;

/**
 * @Author gmd
 * @Description 页面配置解析工具类
 * @Date 2022-09-25 08:47:09
 */
public class PageConfigUtils {

    public static StyleEntity analysisConfig(Map<String, String[]> config) {
        StyleEntity style = new StyleEntity();
        // 是否加粗
        style.setIsBold(config.get("isBold")[0]);
        // 过滤字段
        style.setFilterField("," + Arrays.toString(config.get("filterField")) + ",");
        // 背景颜色
        if (HexUtil.isHexNumber("#" + config.get("backgroundColor")[0])) {
            style.setBackgroundColor(config.get("backgroundColor")[0]);
        }

        // 宽度权重
        Integer lenWeight = 0;
        // 字段列表
        List<WordField> list = new ArrayList<>();

        for (String type : config.get("checkList")) {
            WordField field = WordField.getWordField(type, config.get(type)[0]);
            if (Objects.nonNull(field)) {
                list.add(field);
                lenWeight += field.getWeight();
            }
        }


        // 最后一列提前
        list.add(0, list.remove(list.size() - 1));

        // 添加备注
        WordField field = WordField.getWordField("beizhu", config.get("describeInfo")[0]);
        if (Objects.nonNull(field)) {
            list.add(field);
            lenWeight += field.getWeight();
        }

        style.setWordFields(list);
        style.setLenWeight(lenWeight);
        return style;
    }

}
