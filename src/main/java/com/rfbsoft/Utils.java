package com.rfbsoft;

public class Utils {
    public static boolean isValidLong(String str) {
        if (str == null) return false;
        int len = str.length();
        if (str.charAt(0) == '+') {
            return str.matches("\\+\\d+") && (len < 20 || len == 20 && str.compareTo("+9223372036854775807") <= 0);
        } else if (str.charAt(0) == '-') {
            return str.matches("-\\d+") && (len < 20 || len == 20 && str.compareTo("-9223372036854775808") <= 0);
        } else {
            return str.matches("\\d+") && (len < 19 || len == 19 && str.compareTo("9223372036854775807") <= 0);
        }
    }
}
