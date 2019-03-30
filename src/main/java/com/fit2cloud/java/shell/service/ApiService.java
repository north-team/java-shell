package com.fit2cloud.java.shell.service;

import com.fit2cloud.java.shell.util.ExceptionUtil;
import com.fit2cloud.java.shell.util.HyperVUtil;
import com.fit2cloud.java.shell.util.ResultHolder;
import com.fit2cloud.java.shell.util.ShellUtil;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    public ResultHolder getAllVm() {
        try {
            String res = HyperVUtil.getAllVm();
            return ResultHolder.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(ExceptionUtil.getExceptionMsg(e));
        }
    }

    public ResultHolder getAVm(String name) {
        try {
            if (!name.equals("")){
                String res = HyperVUtil.getAVm(name);
                return ResultHolder.success(res);
            }else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(ExceptionUtil.getExceptionMsg(e));
        }
    }

    public ResultHolder startVm(String name) {
        try {
            if (!name.equals("")){
                String res = HyperVUtil.startVm(name);
                return ResultHolder.success(res);
            }else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(ExceptionUtil.getExceptionMsg(e));
        }
    }

    public ResultHolder suspendVm(String name) {
        try {
            if (!name.equals("")){
                String res = HyperVUtil.suspendVm(name);
                return ResultHolder.success(res);
            }else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(ExceptionUtil.getExceptionMsg(e));
        }
    }

    public ResultHolder resumeVm(String name) {
        try {
            if (!name.equals("")){
                String res = HyperVUtil.resumeVm(name);
                return ResultHolder.success(res);
            }else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(ExceptionUtil.getExceptionMsg(e));
        }
    }

    public ResultHolder stopVm(String name) {
        try {
            if (!name.equals("")){
                String res = HyperVUtil.stopVm(name);
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
