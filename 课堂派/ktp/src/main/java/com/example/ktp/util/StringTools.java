package com.example.ktp.util;

import java.util.UUID;

public class StringTools {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
    public static String generateCode(){
        //去掉“-”符号
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String generateCode(int num){
        if (num < 1){
            return null;
        }
        String uuid = generateCode();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < num; i++) {
            str.append(uuid.charAt(i));
        }
        return str.toString().toUpperCase();
    }
}