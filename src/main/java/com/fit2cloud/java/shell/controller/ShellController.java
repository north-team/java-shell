package com.fit2cloud.java.shell.controller;

import com.fit2cloud.java.shell.service.ShellService;
import com.fit2cloud.java.shell.util.HttpServletUtil;
import com.fit2cloud.java.shell.util.ResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ShellController {
    @Autowired
    ShellService shellService;

    /**
     * 通用接口:获取json格式的字符串，以shell方式执行
     *
     * @return
     */
    @RequestMapping(value = "/exec")
    public ResultHolder execute(HttpServletRequest request) {
        String readerStr = (String) HttpServletUtil.readRequest(request);
        return shellService.execute(readerStr);
    }
}
