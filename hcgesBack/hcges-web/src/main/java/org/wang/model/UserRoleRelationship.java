package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_role_relationship
 */
@TableName(value ="user_role_relationship")
@Data
public class UserRoleRelationship implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String user_ID;

    /**
     * 
     */
    private String role_ID;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}