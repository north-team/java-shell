package com.fit2cloud.java.shell.controller;

import com.fit2cloud.java.shell.config.TokenValid;
import com.fit2cloud.java.shell.service.ApiService;
import com.fit2cloud.java.shell.util.ResultHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 11:29
 */
@RestController
@RequestMapping(value = "api")
@Api(tags = "Hyper_V相关API接口")
public class ApiController {
    @Autowired
    public ApiService apiService;

    /**
     * 校验接口
     *
     * @return
     */
    @ApiOperation("校验服务接口")
    @TokenValid
    @GetMapping(value = "/healthcheck")
    public ResultHolder validates() {
        return ResultHolder.success("Successful connection!");
    }

    /**
     * 获取所有虚机信息
     *
     * @return
     */
    @ApiOperation("获取所有虚机信息")
    @TokenValid
    @GetMapping(value = "/getAllVm")
    public ResultHolder getAllVm() {
        return apiService.getAllVm();
    }

    /**
     * 获取单个虚机信息
     *
     * @return
     */
    @ApiOperation("获取单个虚机信息")
    @TokenValid
    @GetMapping(value = "/getAVm/{name}")
    public ResultHolder getAVm(@PathVariable String name) {
        return apiService.getAVm(name);
    }

    /**
     * 启动虚机
     *
     * @return
     */
    @ApiOperation("启动虚机")
    @TokenValid
    @GetMapping(value = "/startVm/{name}")
    public ResultHolder startVm(@PathVariable String name) {
        return apiService.startVm(name);
    }

    /**
     * 停止虚机
     *
     * @return
     */
    @ApiOperation("停止虚机")
    @TokenValid
    @GetMapping(value = "/stopVm/{name}")
    public ResultHolder stopVm(@PathVariable String name) {
        return apiService.stopVm(name);
    }

    /**
     * 暂停虚机
     *
     * @return
     */
    @ApiOperation("暂停虚机")
    @TokenValid
    @GetMapping(value = "/suspendVm/{name}")
    public ResultHolder suspendVm(@PathVariable String name) {
        return apiService.suspendVm(name);
    }

    /**
     * 恢复虚机
     *
     * @return
     */
    @ApiOperation("恢复虚机")
    @TokenValid
    @GetMapping(value = "/resumeVm/{name}")
    public ResultHolder resumeVm(@PathVariable String name) {
        return apiService.resumeVm(name);
    }


}
