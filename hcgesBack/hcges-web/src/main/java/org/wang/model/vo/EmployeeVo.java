package org.wang.model.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class EmployeeVo {
    /**
     *
     */
    @TableId
    @ExcelIgnore
    private String ID;

    /**
     *
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     *
     */
    @ExcelProperty("性别")
    private String gender;

    /**
     *
     */
    @ExcelProperty("年龄")
    private Integer age;

    /**
     *
     */
    @ExcelProperty("手机号")
    private String phone;

    /**
     *
     */
    @ExcelProperty("身份证号")
    private String ID_card_number;

    /**
     *
     */
    @ExcelIgnore
    private String billing_status;

    /**
     *  单位名称
     */
    @TableField(exist = false)
    @ExcelProperty("所属公司")
    private String unit_name;
}
