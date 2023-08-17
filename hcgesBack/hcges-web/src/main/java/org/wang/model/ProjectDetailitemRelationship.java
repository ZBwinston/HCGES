package org.wang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName project_detailitem_relationship
 */
@TableName(value ="project_detailitem_relationship")
@Data
public class ProjectDetailitemRelationship implements Serializable {
    /**
     * 
     */
    @TableId
    private String ID;

    /**
     * 
     */
    private String project_ID;

    /**
     * 
     */
    private String detail_item_ID;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}