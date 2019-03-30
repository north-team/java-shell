package com.fit2cloud.java.shell.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/28 19:11
 */
public class HttpServletUtil {
    public static Object readRequest(HttpServletRequest request) {
        String str = "";
        String readerStr = "";
        try {
            InputStreamReader in = new InputStreamReader(
                    request.getInputStream(), "utf8");// getInputStream方法返回一个代表实体内容的输入流对象
            BufferedReader reader = new BufferedReader(in);
            while ((str = reader.readLine()) != null) {
                readerStr = readerStr + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.info("传入的接口为 : {}", request.getRequestURI());
        LogUtil.info("传入的参数为 : {}", readerStr);
        return readerStr;
    }
}
