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
 * @TableName log
 */
@TableName(value ="log")
@Data
public class Log implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private Date action_time;

    /**
     * 
     */
    private String operator;

    /**
     * 
     */
    private String action_type;

    /**
     * 
     */
    private String action_content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}