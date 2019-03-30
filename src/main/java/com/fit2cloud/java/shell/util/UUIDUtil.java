

package com.fit2cloud.java.shell.util;

import java.util.UUID;

public class UUIDUtil {
    private UUIDUtil() {
    }

    public static String newUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
