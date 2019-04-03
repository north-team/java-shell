package com.fit2cloud.java.shell.util;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.java.shell.model.NewVm;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 19:17
 */
public class HyperVUtil {

    /**
     * 获取所有虚机信息
     *
     * @return
     */
    public static String getAllVm() {
        return "Get-VM | select *";
    }

    /**
     * 获取所有网络信息
     *
     * @return
     */
    public static String getAllSwitch() {
        return "Get-VMSwitch | select *";
    }

    /**
     * 获取单个虚机信息
     *
     * @param name 虚机名字
     * @return
     */
    public static String getAVm(String name) {
        return "Get-VM -Name '" + name + "' | select *";
    }

    /**
     * 获取单个网络信息
     *
     * @param name 网络名字
     * @return
     */
    public static String getASwitch(String name) {
        return "Get-VMSwitch -Name '" + name + "' | select *";
    }

    /**
     * 启动虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String startVm(String name) {
        return "Start-VM -Name '" + name + "'";
    }

    /**
     * 暂停虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String suspendVm(String name) {
        return "Suspend-VM -Name '" + name + "'";
    }

    /**
     * 恢复虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String resumeVm(String name) {
        return "Resume-VM -Name '" + name + "'";
    }

    /**
     * 保存虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String saveVm(String name) {
        return "Save-VM -Name '" + name + "'";
    }

    /**
     * 关闭虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String stopVm(String name) {
        return "Stop-VM -Name '" + name + "'";
    }

    public static String createVm(String json) {
        String shell = "mkdir -p ${path}\\${name}; " +
                       "cp ${vhdPath}   ${path}\\${name}\\${name}.vhdx; " +
                       "New-VM -VHDPath \"${path}\\${name}\\${name}.vhdx\" -Generation ${generation} -MemoryStartupBytes ${memoryStartupBytes} -BootDevice \"VHD\" -Name \"${name}\" -SwitchName \"${switchName}\" -Path \"${path}\" | select *";
        return StringUtil.replace(json, shell);
    }

    public static void main(String[] args) throws Exception {
        NewVm vm = new NewVm();
        vm.setName("CentOS7-2");
        vm.setVhdPath("E:\\ProgramData\\Microsoft\\Windows\\Hyper-V\\CentOS7.vhdx");
        vm.setGeneration("1");
        vm.setMemoryStartupBytes("1GB");
        vm.setSwitchName("OutSwitch");
        vm.setPath("E:\\ProgramData\\Microsoft\\Windows\\Hyper-V");
        String s = JSONObject.toJSONString(vm);
        String res = createVm(s);
//        String res=ShellUtil.execCmd(createVm(s));
        System.out.println(res);
    }

}
