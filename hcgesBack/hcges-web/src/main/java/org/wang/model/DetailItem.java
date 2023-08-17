package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName detail_item
 */
@TableName(value ="detail_item")
@Data
public class DetailItem implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String detail_item_name;

    /**
     * 
     */
    private String measurement_unit;

    /**
     * 
     */
    private Double upper_limit_value;

    /**
     * 
     */
    private Double lower_limit_value;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}