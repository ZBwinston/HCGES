package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName employee
 */
@TableName(value ="employee")
@Data
public class Employee implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String gender;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String ID_card_number;

    /**
     * 
     */
    private String billing_status;

    /**
     * 
     */
    private String unit_ID;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}