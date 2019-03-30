package com.fit2cloud.java.shell.util;

import org.springframework.stereotype.Component;

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
        return "Get-VM";
    }

    /**
     * 获取单个虚机信息
     *
     * @param name 虚机名字
     * @return
     */
    public static String getAVm(String name) {
        return "Get-VM -Name '" + name + "'";
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

    public static void main(String[] args) {
        String res=stopVm("centos7");
        System.out.println(res);
    }
}
