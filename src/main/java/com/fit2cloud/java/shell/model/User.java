package com.fit2cloud.java.shell.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : zhm
 * @description :
 * @date : 2019/4/1 14:55
 */
public class User {
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
