package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName physical_exam
 */
@TableName(value ="physical_exam")
@Data
public class PhysicalExam implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String unit_ID;

    /**
     * 
     */
    private String employee_ID;

    /**
     * 
     */
    private Date exam_time;

    /**
     * 
     */
    private String package_ID;

    /**
     * 状态
     */
    private String payment_method;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}