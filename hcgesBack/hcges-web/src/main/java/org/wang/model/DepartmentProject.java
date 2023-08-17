package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName department_project
 */
@TableName(value ="department_project")
@Data
public class DepartmentProject implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String department_ID;

    /**
     * 
     */
    private String project_ID;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}