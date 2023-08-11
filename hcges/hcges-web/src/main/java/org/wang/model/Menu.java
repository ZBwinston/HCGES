package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu implements Serializable {
    /**
     * ID
     */
    @TableId
    private String ID;

    /**
     * 菜单名称
     */
    private String menu_name;

    /**
     * 父菜单ID
     */
    private String parent_menu_ID;

    /**
     * 菜单图标
     */
    private String menu_icon;

    /**
     * 菜单链接
     */
    private String menu_link;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}