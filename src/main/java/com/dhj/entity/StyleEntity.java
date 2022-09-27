package com.dhj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author gmd
 * @Description 表单样式
 * @Date 2022-09-24 16:32:58
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StyleEntity implements Serializable {

    /**
     * 背景颜色，16进制颜色编码
     */
    private String backgroundColor;

    /**
     * 是否加粗。0是 1否
     */
    private String isBold;

    /**
     * 过滤字段，多个用英文逗号隔开
     */
    private String filterField;

    /**
     * 字段列表
     */
    private List<WordField> WordFields;

    /**
     * word宽度权重总数
     */
    private Integer lenWeight;

    /**
     * 默认样式配置
     */
    public static StyleEntity getDefaultStyle(){
        return new StyleEntity("A6A6A6", "1", "", WordField.getDefaultFieldList(), 9);
    }

}
