package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName project_information
 */
@TableName(value ="project_information")
@Data
public class ProjectInformation implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String project_name;

    /**
     * 
     */
    private String department_name;

    /**
     * 
     */
    private String project_type;

    /**
     * 
     */
    private Double price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}