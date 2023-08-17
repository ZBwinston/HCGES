package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName role_menu
 */
@TableName(value ="role_menu")
@Data
public class RoleMenu implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String role_ID;

    /**
     * 
     */
    private String menu_PID;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}