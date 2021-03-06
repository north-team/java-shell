package com.fit2cloud.java.shell.controller;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.java.shell.config.TokenValid;
import com.fit2cloud.java.shell.model.NewVm;
import com.fit2cloud.java.shell.service.ApiService;
import com.fit2cloud.java.shell.model.ResultHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 11:29
 */
@RestController
@RequestMapping(value = "api/v1")
@Api(tags = "Hyper-V相关API接口")
public class ApiController {
    @Autowired
    public ApiService apiService;

    /**
     * 获取所有虚机信息
     *
     * @return
     */
    @ApiOperation("获取所有虚机信息")
    @TokenValid
    @GetMapping(value = "vm/getAll")
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
    @GetMapping(value = "vm/get")
    public ResultHolder getAVm(@RequestParam String name) {
        return apiService.getAVm(name);
    }

    /**
     * 启动虚机
     *
     * @return
     */
    @ApiOperation("启动虚机")
    @TokenValid
    @GetMapping(value = "vm/start")
    public ResultHolder startVm(@RequestParam String name) {
        return apiService.startVm(name);
    }

    /**
     * 停止虚机
     *
     * @return
     */
    @ApiOperation("停止虚机")
    @TokenValid
    @GetMapping(value = "vm/stop")
    public ResultHolder stopVm(@RequestParam String name) {
        return apiService.stopVm(name);
    }

    /**
     * 暂停虚机
     *
     * @return
     */
    @ApiOperation("暂停虚机")
    @TokenValid
    @GetMapping(value = "vm/suspend")
    public ResultHolder suspendVm(@RequestParam String name) {
        return apiService.suspendVm(name);
    }

    /**
     * 恢复虚机
     *
     * @return
     */
    @ApiOperation("恢复虚机")
    @TokenValid
    @GetMapping(value = "vm/resume")
    public ResultHolder resumeVm(@RequestParam String name) {
        return apiService.resumeVm(name);
    }

    /**
     * 恢复虚机
     *
     * @return
     */
    @ApiOperation(value = "根据模板创建虚机",consumes = "application/x-www-form-urlencoded")
    @TokenValid
    @PostMapping(value = "vm/create")
    public ResultHolder createVm(@RequestBody NewVm vm) {
        return apiService.createVm(JSONObject.toJSONString(vm));
    }

    /**
     * 获取所有网络信息
     *
     * @return
     */
    @ApiOperation("获取所有网络信息")
    @TokenValid
    @GetMapping(value = "switch/getAll")
    public ResultHolder getAllSwitch() {
        return apiService.getAllSwitch();
    }

    /**
     * 获取单个网络信息
     *
     * @return
     */
    @ApiOperation("获取单个网络信息")
    @TokenValid
    @GetMapping(value = "switch/get")
    public ResultHolder getASwitch(@RequestParam String name) {
        return apiService.getASwitch(name);
    }
    /**
     * 获取单个网络信息
     *
     * @return
     */
    @ApiOperation("获取所有模版信息")
    @TokenValid
    @GetMapping(value = "template/getAll")
    public ResultHolder getAllTemplate() {
        return apiService.getAllTemplate();
    }
}
