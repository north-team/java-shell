package com.fit2cloud.java.shell.controller;

import com.fit2cloud.java.shell.service.ApiService;
import com.fit2cloud.java.shell.service.ShellService;
import com.fit2cloud.java.shell.util.ResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApiController {
    @Autowired
    public ApiService apiService;

    /**
     * 校验接口
     *
     * @return
     */
    @RequestMapping(value = "/validate")
    public ResultHolder validates() {
        return ResultHolder.success("Successful connection!");
    }

    /**
     * 获取所有虚机信息
     *
     * @return
     */
    @RequestMapping(value = "/getAllVm")
    public ResultHolder stopVm() {
        return apiService.getAllVm();
    }

    /**
     * 获取单个虚机信息
     *
     * @return
     */
    @RequestMapping(value = "/getAVm/{name}")
    public ResultHolder getAVm(@PathVariable String name) {
        return apiService.getAVm(name);
    }

    /**
     * 启动虚机
     *
     * @return
     */
    @RequestMapping(value = "/startVm/{name}")
    public ResultHolder startVm(@PathVariable String name) {
        return apiService.startVm(name);
    }

    /**
     * 停止虚机
     *
     * @return
     */
    @RequestMapping(value = "/stopVm/{name}")
    public ResultHolder stopVm(@PathVariable String name) {
        return apiService.stopVm(name);
    }

    /**
     * 暂停虚机
     *
     * @return
     */
    @RequestMapping(value = "/suspendVm/{name}")
    public ResultHolder suspendVm(@PathVariable String name) {
        return apiService.suspendVm(name);
    }

    /**
     * 恢复虚机
     *
     * @return
     */
    @RequestMapping(value = "/resumeVm/{name}")
    public ResultHolder resumeVm(@PathVariable String name) {
        return apiService.resumeVm(name);
    }


}
