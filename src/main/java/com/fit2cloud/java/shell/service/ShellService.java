package com.fit2cloud.java.shell.service;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.java.shell.config.CommonThreadPool;
import com.fit2cloud.java.shell.model.ResultHolder;
import com.fit2cloud.java.shell.util.CodingUtil;
import com.fit2cloud.java.shell.util.ShellUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 12:24
 */
@Service
public class ShellService {
    @Autowired
    Environment environment;
    @Resource
    private CommonThreadPool commonThreadPool = new CommonThreadPool();

    public ResultHolder execCmdJson(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Collection<Object> values = jsonObject.values();
        List<Object> stringList = new ArrayList(values);
        try {
            if (!json.equals("")) {
                return ResultHolder.success(execCmd(listToString(stringList, ";")));
            } else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public String execCmd(String cmd) throws Exception {
        return commonThreadPool.addTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return ShellUtil.execCmd(cmd);
            }
        }).get();
    }

    public ResultHolder login(String usname, String password) {
        try {
            if (usname.equals(environment.getProperty("login.username")) && password.equals(environment.getProperty("login.password"))) {
                String token = CodingUtil.md5("Fit2cloudShellToken:" + usname + password);
                return ResultHolder.success(token);
            } else {
                return ResultHolder.error("登录失败！用户名或密码校验失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public static String listToString(List list, String separator) {
        return StringUtils.join(list.toArray(), separator);
    }
}
