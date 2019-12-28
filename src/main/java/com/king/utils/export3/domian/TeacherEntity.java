package com.king.utils.export3.domian;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ExcelTarget("teacherEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeacherEntity implements java.io.Serializable {
    private String id;
    @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1", needMerge = true, isImportField = "true_major,true_absent")
    private String name;
}
