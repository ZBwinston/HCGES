package org.wang.model.vo;

import lombok.Data;

@Data
public class PhysicalExamVo {

    private String ID;

    private String name;

    private String project_name;

    private String department_name;

    /**
     * 状态
     */
    private String payment_method;

}
