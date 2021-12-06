package com.islet.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";
    private static final String saltDB = "a1b2c3d4";

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass) {
        String str = ""+saltDB.charAt(0) + saltDB.charAt(2) + formPass +saltDB.charAt(5) + saltDB.charAt(4);
        return md5(str);
    }

    public static void main(String [] args) {
        String ykhwzx2019 = inputPassToFormPass("123456");
        System.out.println(ykhwzx2019);
		String s = formPassToDBPass("123456");
		System.out.println("s = " + s);
    }

}
