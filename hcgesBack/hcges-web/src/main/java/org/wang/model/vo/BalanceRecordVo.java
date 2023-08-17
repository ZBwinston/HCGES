package org.wang.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BalanceRecordVo {

    /**
     * 对账单编号
     */
    @TableId
    private String ID;

    /**
     * 公司名称
     */
    private String unit_name;

    /**
     *  流水类型
     */
    private String action_type;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 时间
     */
    private Date action_time;
}
