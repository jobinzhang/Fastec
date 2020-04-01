package com.example.latte_core.util;

public class Utils {

    public static long toLong(String obj, long defaultValue) {
        if (obj == null || "".endsWith(obj)) {
            return defaultValue;
        }
        return Long.valueOf(obj);
    }
}
