package com.example.FileCheck.shared;

public class Utils {

    public static boolean isMimeTypeImage(String value) {
        if (value != null) {
            String[] s = value.toLowerCase().split("/");
            return "image".equals(s[0]);
        }
        return false;
    }
}
