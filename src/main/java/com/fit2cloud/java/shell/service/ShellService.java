package com.fit2cloud.java.shell.service;

import com.fit2cloud.java.shell.util.ExceptionUtil;
import com.fit2cloud.java.shell.util.ResultHolder;
import com.fit2cloud.java.shell.util.ShellUtil;
import org.springframework.stereotype.Service;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 12:24
 */
@Service
public class ShellService {

    public ResultHolder execute(String json) {
        try {
            if (!json.equals("")){
                String res = ShellUtil.execCmdJsonString(json, null);
                return ResultHolder.success(res);
            }else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(ExceptionUtil.getExceptionMsg(e));
        }
    }
}
