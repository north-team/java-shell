package com.fit2cloud.java.shell.util;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Iterator;

public class StringUtil {
    public static String replace(String json, String shell) {
        JSONObject jsonObject = new JSONObject(json);
        Iterator iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = jsonObject.getString(key);
            shell = StringUtils.replace(shell, "${" + key + "}", value);
        }
        return shell;
    }
}
