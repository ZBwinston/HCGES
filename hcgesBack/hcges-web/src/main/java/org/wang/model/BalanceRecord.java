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
 * @TableName balance_record
 */
@TableName(value ="balance_record")
@Data
public class BalanceRecord implements Serializable {
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
    private String action_type;

    /**
     * 
     */
    private Double amount;

    /**
     * 
     */
    private Date action_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}