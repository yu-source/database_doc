package com.dhj.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@ColumnWidth(25)
@AllArgsConstructor
public class ExcelEntity implements Serializable {

    @ColumnWidth(15)
    @ExcelProperty(value = "序号", index = 0)
    private String number;

    @ColumnWidth(30)
    @ExcelProperty(value = "字段名", index = 1)
    private String fieldName;

    @ColumnWidth(30)
    @ExcelProperty(value = "类型(长度)", index = 2)
    private String type;

    @ColumnWidth(15)
    @ExcelProperty(value = "主键", index = 3)
    private String mainKey;

    @ColumnWidth(30)
    @ExcelProperty(value = "描述", index = 4)
    private String desc;

    @ColumnWidth(50)
    @ExcelProperty(value = "表名", index = 5)
    private String tableName;

    public ExcelEntity(String fieldName) {
        this.fieldName = fieldName;
    }

    public ExcelEntity() {
        this.number = "序号";
        this.fieldName = "字段名";
        this.type = "类型(长度)";
        this.mainKey = "主键";
        this.desc = "描述";
    }

}
