package com.fit2cloud.java.shell.service;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.java.shell.model.NewVm;
import com.fit2cloud.java.shell.model.ResultHolder;
import com.fit2cloud.java.shell.util.HyperVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    @Autowired
    ShellService shellService;

    public ResultHolder getAllVm() {
        try {
            String res = shellService.execCmd(HyperVUtil.getAllVm());
            return ResultHolder.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder getAVm(String name) {
        try {
            if (!name.equals("")) {
                String res = shellService.execCmd(HyperVUtil.getAVm(name));
                return ResultHolder.success(res);
            } else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder startVm(String name) {
        try {
            if (!name.equals("")) {
                String res = shellService.execCmd(HyperVUtil.startVm(name));
                if (res.equals("")) {
                    return ResultHolder.success(res);
                } else {
                    return ResultHolder.error(res);
                }
            } else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder suspendVm(String name) {
        try {
            if (!name.equals("")) {
                String res = shellService.execCmd(HyperVUtil.suspendVm(name));
                if (res.equals("")) {
                    return ResultHolder.success(res);
                } else {
                    return ResultHolder.error(res);
                }
            } else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder resumeVm(String name) {
        try {
            if (!name.equals("")) {
                String res = shellService.execCmd(HyperVUtil.resumeVm(name));
                if (res.equals("")) {
                    return ResultHolder.success(res);
                } else {
                    return ResultHolder.error(res);
                }
            } else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder stopVm(String name) {
        try {
            if (!name.equals("")) {
                String res = shellService.execCmd(HyperVUtil.stopVm(name));
                if (res.equals("")) {
                    return ResultHolder.success(res);
                } else {
                    return ResultHolder.error(res);
                }
            } else {
                return ResultHolder.error("传入参数不能为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder createVm(String vm) {
        try {
            String res = shellService.execCmd(HyperVUtil.createVm(vm));
            return ResultHolder.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder getAllSwitch() {
        try {
            String res = shellService.execCmd(HyperVUtil.getAllSwitch());
            return ResultHolder.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

    public ResultHolder getASwitch(String name) {
        try {
            String res = shellService.execCmd(HyperVUtil.getASwitch(name));
            return ResultHolder.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultHolder.error(e.toString());
        }
    }

}
