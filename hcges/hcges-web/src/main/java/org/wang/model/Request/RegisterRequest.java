package org.wang.model.Request;

import lombok.Data;

@Data
public class RegisterRequest {

    private Integer code; // 0-用户名重复 1-注册成功
    private String rMsg;
}
