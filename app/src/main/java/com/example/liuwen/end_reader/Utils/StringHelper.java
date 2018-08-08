package com.example.liuwen.end_reader.Utils;

import java.net.URLEncoder;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/08 14:36
 * desc   :
 */
public class StringHelper {

    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.replace(" ", "");
        }
        return str == null || str.equals("");
    }

    /**
     * 字符集编码
     * @param encoded
     * @return
     */
    public static String encode(String encoded){
        String res = encoded;
        try {
            res = URLEncoder.encode(encoded,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

}
