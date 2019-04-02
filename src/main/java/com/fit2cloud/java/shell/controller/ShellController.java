package com.fit2cloud.java.shell.controller;

import com.fit2cloud.java.shell.config.TokenValid;
import com.fit2cloud.java.shell.model.User;
import com.fit2cloud.java.shell.service.ShellService;
import com.fit2cloud.java.shell.model.ResultHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 11:29
 */
@RestController
@RequestMapping(value = "shell")
@Api(tags = "Shell接口")
public class ShellController {
    @Autowired
    ShellService shellService;

    /**
     * 通用接口:获取json格式的字符串，以shell方式执行
     *
     * @return
     */
    @ApiOperation("通用执行接口:获取json格式的字符串，以shell方式执行")
    @ApiImplicitParam(name = "jsonString", value = "Json形式的字符串", required = true, dataType = "string")
    @TokenValid
    @PostMapping(value = "/exec")
    public ResultHolder execute(@RequestBody String jsonString) {
        return shellService.execute(jsonString);
    }
    /**
     * 登录接口:校验用户名密码获取相应的token
     *
     * @return
     */
    @ApiOperation("登录接口:校验用户名密码获取相应的token")
    @ApiImplicitParam(name = "shellToken", value = "用户token", required = false, dataType = "String",paramType="header")
    @PostMapping(value = "/login")
    public ResultHolder login(@RequestBody User user) {
        return shellService.login(user.getUsername(),user.getPassword());
    }
    /**
     * 校验接口:校验服务是否启动
     *
     * @return
     */
    @ApiOperation("校验服务接口")
    @ApiImplicitParam(name = "shellToken", value = "用户token", required = false, dataType = "String",paramType="header")
    @GetMapping(value = "/healthcheck")
    public ResultHolder validates() {
        return ResultHolder.success("Successful connection!");
    }
}
