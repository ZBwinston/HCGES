package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName unit
 */
@TableName(value ="unit")
@Data
public class Unit implements Serializable {
    /**
     *  单位ID
     */
    @TableId
    private String ID;

    /**
     *  单位名称
     */
    private String unit_name;

    /**
     * 联系人
     */
    private String contact_person;

    /**
     * 电话
     */
    private String phone;

    /**
     * 账户余额
     */
    private Double account_balance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}