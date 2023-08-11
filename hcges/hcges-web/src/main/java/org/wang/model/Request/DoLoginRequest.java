package org.wang.model.Request;

import lombok.Data;

import java.io.Serializable;

@Data
public class DoLoginRequest implements Serializable {

    private String username;
    private String password;

}
