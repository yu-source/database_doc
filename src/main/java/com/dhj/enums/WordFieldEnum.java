package com.dhj.enums;

/**
 * @Author gmd
 * @Description word字段枚举类
 * @Date 2022-09-26 21:11:30
 */
public enum WordFieldEnum {

    SERIAL_NO("serialNo", "序号", 1),
    FIELD_NAME("fieldName", "字段名", 2),
    TYPE("type", "类型", 1);

    
    
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


    WordFieldEnum(String key, String name, Integer weight) {
        this.key = key;
        this.name = name;
        this.weight = weight;
    }


}
