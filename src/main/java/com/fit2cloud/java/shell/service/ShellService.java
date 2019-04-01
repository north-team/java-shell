package com.fit2cloud.java.shell.service;

import com.fit2cloud.java.shell.util.CodingUtil;
import com.fit2cloud.java.shell.util.ResultHolder;
import com.fit2cloud.java.shell.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 12:24
 */
@Service
public class ShellService {
    @Autowired
    Environment environment;

    public ResultHolder execute(String json) {
        try {
            if (!json.equals("")) {
                String res = ShellUtil.execCmdJsonString(json, null);
                return ResultHolder.success(res);
            } else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
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
}
