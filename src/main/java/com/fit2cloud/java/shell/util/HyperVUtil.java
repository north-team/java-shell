package com.fit2cloud.java.shell.util;

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
    public static String StartVm(String name) {
        return "Start-VM -Name '" + name + "'";
    }

    /**
     * 暂停虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String SuspendVm(String name) {
        return "Suspend-VM -Name '" + name + "'";
    }

    /**
     * 恢复虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String ResumeVm(String name) {
        return "Resume-VM -Name '" + name + "'";
    }

    /**
     * 保存虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String SaveVm(String name) {
        return "Save-VM -Name '" + name + "'";
    }

    /**
     * 关闭虚拟机
     *
     * @param name 虚机名字
     * @return
     */
    public static String StopVm(String name) {
        return "Stop-VM -Name '" + name + "'";
    }

    public static void main(String[] args) {
        String res=StopVm("centos7");
        System.out.println(res);
    }
}
