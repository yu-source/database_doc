package com.dhj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author gmd
 * @Description 表单样式
 * @Date 2022-09-24 16:32:58
 */
@Data
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
     * 过滤字段
     */
    private String[] filterField;

    /**
     * 字段列表
     */
    private WordEntity wordEntity;

    /**
     * 字段长度
     */
    private Integer filedLen;

}
