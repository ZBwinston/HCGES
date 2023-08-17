package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName department
 */
@TableName(value ="department")
@Data
public class Department implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String department_name;

    /**
     * 
     */
    private String manager;

    /**
     * 
     */
    private String department_introduction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}