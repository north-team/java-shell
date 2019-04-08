package com.fit2cloud.java.shell.util;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.List;

/**
 * @author : zhm
 * @description :
 * @date : 2019/4/8 11:02
 */
public class FileUtil {
    /*
    使用递归找出某目录("C:\\JavaProducts")下的所有子目录以及子文件
    */
    public static void main(String[] args) {
//        List<String> paths = new ArrayList<String>();
//        paths = getAllFilePaths(new File("/opt"), paths);
//        for (String path : paths) {
//            System.out.println(path);
//        }
        JSONObject object = new JSONObject();
        object = getAllFilePaths(new File("/opt/fit2cloud/share/plugins/"), object);
        System.out.println(object.toJSONString());
    }

    public static List<String> getAllFilePaths(File filePath, List<String> filePaths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return filePaths;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                filePaths.add(f.getPath());
                getAllFilePaths(f, filePaths);
            } else {
                filePaths.add(f.getPath());
            }
        }
        return filePaths;
    }

    public static JSONObject getAllFilePaths(File filePath, JSONObject filePaths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return filePaths;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                filePaths.put(f.getPath().substring(f.getPath().lastIndexOf("/") + 1), f.getPath());
                getAllFilePaths(f, filePaths);
            } else {
                filePaths.put(f.getPath().substring(f.getPath().lastIndexOf("/") + 1), f.getPath());
            }
        }
        return filePaths;
    }

}
