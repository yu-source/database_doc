package com.dhj.entity;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author gmd
 * @Description word字段枚举类
 * @Date 2022-09-26 21:11:30
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WordField {

    /**
     * 字段key
     */
    private String key;
    /**
     * 字段名
     */
    private String name;
    /**
     * 列宽权重
     */
    private Integer weight;

    /**
     * 获取默认字段列表
     */
    public static List<WordField> getDefaultFieldList() {
        List<WordField> list = new ArrayList<>();
        // 序号
        list.add(getWordField("serialNo", null));
        // 字段名
        list.add(getWordField("fieldName", null));
        // 类型(长度)
        list.add(getWordField("typeAndLen", null));
        // 主键
        list.add(getWordField("mainKey", null));
        // 描述
        list.add(getWordField("describeInfo", null));

        return list;
    }

    /**
     * 根据类型获取字段
     */
    public static WordField getWordField(String type, String name) {
        switch (type) {
            case "serialNo":
                return new WordField("serialNo", StrUtil.blankToDefault(name, "序号"), 1);
            case "fieldName":
                return new WordField("fieldName", StrUtil.blankToDefault(name, "字段名"), 2);
            case "type":
                return new WordField("type", StrUtil.blankToDefault(name, "类型"), 1);
            case "length":
                return new WordField("length", StrUtil.blankToDefault(name, "长度"), 1);
            case "typeAndLen":
                return new WordField("typeAndLen", StrUtil.blankToDefault(name, "类型(长度)"), 2);
            case "precision":
                return new WordField("precision", StrUtil.blankToDefault(name, "精度"), 1);
            case "numScale":
                return new WordField("numScale", StrUtil.blankToDefault(name, "小数位数"), 1);
            case "allowEmpty":
                return new WordField("allowEmpty", StrUtil.blankToDefault(name, "允许为空"), 1);
            case "mainKey":
                return new WordField("mainKey", StrUtil.blankToDefault(name, "主键"), 1);
            case "defaultVal":
                return new WordField("defaultVal", StrUtil.blankToDefault(name, "默认值"), 1);
            case "describeInfo":
                return new WordField("describeInfo", StrUtil.blankToDefault(name, "描述"), 3);
        }
        return null;
    }


}
