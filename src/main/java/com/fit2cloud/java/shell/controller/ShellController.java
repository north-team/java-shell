package com.fit2cloud.java.shell.controller;

import com.fit2cloud.java.shell.config.TokenValid;
import com.fit2cloud.java.shell.model.User;
import com.fit2cloud.java.shell.service.ShellService;
import com.fit2cloud.java.shell.util.HttpServletUtil;
import com.fit2cloud.java.shell.util.ResultHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
     * 通用执行接口:获取json格式的字符串，以shell方式执行
     *
     * @return
     */
    @ApiOperation("通用执行接口:获取json格式的字符串，以shell方式执行")
    @TokenValid
    @PostMapping(value = "/exec")
    public ResultHolder execute(HttpServletRequest request) {
        String readerStr = (String) HttpServletUtil.readRequest(request);
        return shellService.execute(readerStr);
    }
    /**
     * 登录接口:校验用户名密码获取相应的token
     *
     * @return
     */
    @ApiOperation("登录接口:校验用户名密码获取相应的token")
    @PostMapping(value = "/login")
    public ResultHolder login(@RequestBody User user) {
        return shellService.login(user.getUsername(),user.getPassword());
    }
}
