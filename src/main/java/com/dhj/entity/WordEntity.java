package com.dhj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author gmd
 * @Description 表单允许的主键
 * @Date 2022-09-24 16:32:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordEntity {

    /**
     * 序号
     */
    private String serialNo;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 类型
     */
    private String type;

    /**
     * 长度
     */
    private String length;

    /**
     * 类型(长度)
     */
    private String typeAndLen;

    /**
     * 精度
     */
    private String precision;

    /**
     * 小数位数
     */
    private String numScale;

    /**
     * 允许为空
     */
    private String allowEmpty;

    /**
     * 主键
     */
    private String mainKey;

    /**
     * 默认值
     */
    private String defaultVal;

    /**
     * 描述
     */
    private String describeInfo;

    /**
     * 默认字段配置
     */
    public static WordEntity getDefaultField() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.serialNo = "序号";
        wordEntity.fieldName = "字段名";
        wordEntity.typeAndLen = "类型(长度)";
        wordEntity.mainKey = "主键";
        wordEntity.describeInfo = "描述";
        return wordEntity;
    }

}
