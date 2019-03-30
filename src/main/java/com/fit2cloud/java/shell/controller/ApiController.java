package com.fit2cloud.java.shell.controller;

import com.fit2cloud.java.shell.service.ShellService;
import com.fit2cloud.java.shell.util.ResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
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
    ShellService shellService;

    @RequestMapping(value = "/validate")
    public ResultHolder validates() throws InterruptedException {
        return ResultHolder.success("Successful connection!");
    }
}
