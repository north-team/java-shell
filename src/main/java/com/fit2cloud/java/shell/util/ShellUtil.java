package com.fit2cloud.java.shell.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 12:26
 */
public class ShellUtil {

//    1.每个命令之间用;隔开 \n
//    说明：各命令的执行给果，不会影响其它命令的执行。换句话说，各个命令都会执行，但不保证每个命令都执行成功。
//    2.每个命令之间用&&隔开
//    说明：若前面的命令执行成功，才会去执行后面的命令。这样可以保证所有的命令执行完毕后，执行过程都是成功的。
//    3.每个命令之间用||隔开
//    说明：||是或的意思，只有前面的命令执行失败后才去执行下一条命令，直到执行成功一条命令为止。

    public static void main(String[] args) throws Exception{
        List<Object> stringList = new ArrayList();
        stringList.add("Get-VM");
        stringList.add("ipconfig");
        String result = execCmd(listToString(stringList, ";"), null);

        System.out.println(result);
    }

    public static String execCmdJsonString(String cmd, File dir) throws Exception{
        JSONObject jsonObject = JSONObject.parseObject(cmd);
        Collection<Object> values = jsonObject.values();
        List<Object> stringList = new ArrayList(values);
        return execCmd(listToString(stringList, ";"), dir);
    }

    public static String execCmdMap(Map map, File dir) throws Exception{
        Collection<Object> values = map.values();
        List<Object> stringList = new ArrayList<>(values);
        return execCmd(listToString(stringList, ";"), dir);
    }

    /**
     * 执行系统命令, 返回执行结果
     *
     * @param cmd 需要执行的命令
     * @param dir 执行命令的子进程的工作目录, null 表示和当前主进程工作目录相同
     */
    public static String execCmd(String cmd, File dir) throws Exception {
        String[] cmdLinux = {"/bin/sh", "-c", cmd};
        String[] cmdWin = {"C:\\Windows\\SysWOW64\\WindowsPowerShell\\v1.0\\powershell.exe", cmd};
        StringBuilder result = new StringBuilder();

        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;

        try {
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(cmdWin, null, dir);

            // 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();

            // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GB2312"));

            // 读取输出
            String line = null;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append('\n');
            }
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append('\n');
            }
            closeStream(bufrIn);
            closeStream(bufrError);

            // 销毁子进程
            if (process != null) {
                process.destroy();
            }
            LogUtil.getLogger().info("Process execCmd:{}", "\n" + result.toString());
            // 返回执行结果
        } catch (Exception e) {
            LogUtil.getLogger().error("Process Error：{}", ExceptionUtil.getExceptionMsg(e));
            throw new Exception(e);
        }
        return result.toString();
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                LogUtil.getLogger().error("Process closeStream：{}", ExceptionUtil.getExceptionMsg(e));
            }
        }
    }


    public static String listToString(List list, String separator) {
        return StringUtils.join(list.toArray(), separator);
    }
}
